package com.lnTime.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SliderImagesService {

    void delete(Integer id);

    byte[] getImageById(Integer id);

    void save(byte[] imageBytes);

    List<Integer> getAllIds();
}
