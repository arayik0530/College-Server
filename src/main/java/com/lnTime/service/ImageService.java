package com.lnTime.service;

import com.lnTime.domain.ImageEntity;

public interface ImageService {

    void delete (Long id);

    void save(byte[] imagesBytes, Long itemId);

    byte[] getImageBytesById(Long imageId);

    ImageEntity findById(Long imageId);
}
