package com.lnTime.service;

import com.lnTime.dto.item.ItemDTO;
import com.lnTime.dto.subCategory.CreateSubCategoryDTO;
import com.lnTime.dto.subCategory.SubCategoryDTO;

import java.util.List;

public interface SubCategoryService {

    SubCategoryDTO findById(Long id);

    void remove(Long id);

    void save(CreateSubCategoryDTO createSubCategoryDTO);

    void update(SubCategoryDTO subCategoryDTO, Long id);

    List<SubCategoryDTO> getAll();

    List<ItemDTO> getItems(Long subCategoryId);

    String getPath(Long subCategoryId);

}
