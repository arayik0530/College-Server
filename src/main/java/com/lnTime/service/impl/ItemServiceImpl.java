package com.lnTime.service.impl;

import com.lnTime.domain.ImageEntity;
import com.lnTime.domain.ItemEntity;
import com.lnTime.domain.SubCategoryEntity;
import com.lnTime.dto.item.CreateItemDTO;
import com.lnTime.dto.item.ItemDTO;
import com.lnTime.repository.ItemRepository;
import com.lnTime.repository.SubCategoryRepository;
import com.lnTime.service.ImageService;
import com.lnTime.service.ItemService;
import com.lnTime.service.util.exception.ItemNotFoundException;
import com.lnTime.service.util.exception.SubCategoryNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private ImageService imageService;

    @Override
    public Page<ItemDTO> findByTitleOrDescription(String param, Pageable pageable) {
        List<ItemDTO> itemDTOS =  itemRepository
                .searchByParam(param, pageable)
                .stream()
                .map(i -> ItemDTO.mapFromEntity(i))
                .collect(Collectors.toList());
        return new PageImpl<>(itemDTOS);
    }

    @Override
    public ItemEntity findById(Long id) {
        Optional<ItemEntity> byId = itemRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            throw new ItemNotFoundException(id);
        }
    }

    @Override
    @Transactional
    public void remove(Long id) {
        Optional<ItemEntity> byId = itemRepository.findById(id);
        if (byId.isPresent()) {
            itemRepository.delete(byId.get());
        }
    }

    @Override
    @Transactional
    public void save(CreateItemDTO createItemDTO) {
        ItemEntity itemEntity = new ItemEntity();
        Optional<SubCategoryEntity> byId = subCategoryRepository.findById(createItemDTO.getSubCategoryId());
        if(byId.isPresent())
            itemEntity.setSubCategory(byId.get());
        else
            throw new SubCategoryNotFoundException(createItemDTO.getSubCategoryId());
        itemRepository.save(createItemDTO.toEntity(itemEntity));
     }

    @Override
    @Transactional
    public void update(ItemDTO item, Long id) {
        Optional<ItemEntity> byId = itemRepository.findById(id);
        if (byId.isPresent()) {
            ItemEntity itemEntity = byId.get();
            item.toEntity(itemEntity);
            itemRepository.save(itemEntity);
        } else {
            throw new ItemNotFoundException(id);
        }
    }

    @Override
    @Transactional
    public void saveImage(MultipartFile image, Long itemId) {
        try {
            imageService.save(image.getBytes(), itemId);

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    @Transactional
    public void deleteImage(Long imageId, Long itemId) {
        ImageEntity imageEntity = imageService.findById(imageId);
        Optional<ItemEntity> byId = itemRepository.findById(itemId);
        if (byId.isPresent()) {
            //TODO must be tested from front
            byId.get().getImages().remove(imageEntity);
        } else {
            throw new ItemNotFoundException(itemId);
        }
        //TODO must be tested from front
        imageService.delete(imageId);

    }

    @Override
    public List<ImageEntity> getAlImages(Long itemId) {
        Optional<ItemEntity> byId = itemRepository.findById(itemId);
        if (byId.isPresent()) {
            return byId.get().getImages();
        } else {
            throw new ItemNotFoundException(itemId);
        }
    }
}
