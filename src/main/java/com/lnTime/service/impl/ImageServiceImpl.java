package com.lnTime.service.impl;

import com.lnTime.domain.ImageEntity;
import com.lnTime.domain.ItemEntity;
import com.lnTime.repository.ImageRepository;
import com.lnTime.repository.ItemRepository;
import com.lnTime.service.ImageService;
import com.lnTime.service.util.exception.ImageNotFoundException;
import com.lnTime.service.util.exception.ItemNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    @Transactional()
    public void delete(Long id) {
        Optional<ImageEntity> byId = imageRepository.findById(id);
        if (byId.isPresent()) {
            imageRepository.delete(byId.get());
        }
    }

    @Override
    @Transactional()
    public void save(byte[] imageBytes, Long itemId) {
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setPicture(imageBytes);

        ItemEntity itemEntity = itemRepository.findById(itemId).orElseThrow(() -> new ItemNotFoundException(itemId));

        imageRepository.save(imageEntity);

        itemEntity.getImages().add(imageEntity);
        itemRepository.save(itemEntity);
    }

    @Override
    public ImageEntity findById(Long imageId) {
       Optional<ImageEntity> byId = imageRepository.findById(imageId);
       if(byId.isPresent()){
           return byId.get();
       }
       else {
           throw new ImageNotFoundException(imageId);
       }
    }
}
