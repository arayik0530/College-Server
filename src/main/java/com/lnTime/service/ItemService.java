package com.lnTime.service;

import com.lnTime.domain.ImageEntity;
import com.lnTime.domain.ItemEntity;
import com.lnTime.dto.item.ItemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItemService {

    Page<ItemEntity> findByTitleOrDescription(String param, Pageable pageable);

    ItemDTO findById(Long id);

    void remove(Long id);

    void save(ItemDTO item);

    void update(ItemDTO item);

    void saveImage(MultipartFile image, Long itemId);

    void deleteImage(Long imageId, Long itemId);

    List<ImageEntity> getAlImages(Long itemId);
}
