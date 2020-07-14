package com.lnTime.service;

public interface ImageService {

    void delete (Long id);

    void save(byte[] imagesBytes, Long itemId);
}
