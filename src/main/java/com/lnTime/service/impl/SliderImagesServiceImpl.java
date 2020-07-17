package com.lnTime.service.impl;

import com.lnTime.domain.SliderImagesEntity;
import com.lnTime.repository.SliderImagesRepository;
import com.lnTime.service.SliderImagesService;
import com.lnTime.service.util.exception.SliderImageNotFoundException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@Data
public class SliderImagesServiceImpl implements SliderImagesService {
    @Autowired
    private SliderImagesRepository sliderImagesRepository;

    @Override
    @Transactional
    public void delete(Integer id) {
        Optional<SliderImagesEntity> byId = sliderImagesRepository.findById(id);
        if(byId.isPresent())
            sliderImagesRepository.delete(byId.get());
        else
            throw new SliderImageNotFoundException(id);
    }

    @Override
    public byte[] getImageById(Integer id) {
        Optional<SliderImagesEntity> byId = sliderImagesRepository.findById(id);
        if(byId.isPresent()){
            return byId.get().getPicture();
        }
        else {
            throw new SliderImageNotFoundException(id);
        }
    }

    @Override
    @Transactional
    public void save(byte[] imageBytes) {
        SliderImagesEntity sliderImagesEntity = new SliderImagesEntity();
        sliderImagesEntity.setPicture(imageBytes);

        sliderImagesRepository.save(sliderImagesEntity);
    }

    @Override
    public List<Integer> getAllIds() {
        return sliderImagesRepository.findAll()
                .stream()
                .map(SliderImagesEntity::getId)
                .collect(Collectors.toList());
    }
}
