package com.lnTime.service;

import com.lnTime.domain.ImageEntity;
import com.lnTime.domain.ItemEntity;
import com.lnTime.dto.item.CreateItemDTO;
import com.lnTime.dto.item.ItemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItemService {

    Page<ItemDTO> findByTitleOrDescription(String param, Pageable pageable);



    List<ItemDTO> findTopNItems(Long n, Long categoryId);

    ItemDTO findById(Long id);

    void remove(Long id);

    void save(CreateItemDTO createItemDTO);

    void update(ItemDTO item, Long id);

    void saveImage(MultipartFile image, Long itemId);

    void deleteImage(Long imageId, Long itemId);

    List<Long> getAlImagesIds(Long itemId);

    List<String> getAlImagesURLs(Long itemId);

    String getPath(Long ItemId);
}
