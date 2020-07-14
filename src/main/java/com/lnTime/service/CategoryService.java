package com.lnTime.service;

import com.lnTime.dto.category.CategoryDTO;
import com.lnTime.dto.subCategory.SubCategoryDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO findById(Long id);

    void remove(Long id);

    void save(CategoryDTO categoryDTO);

    void update(CategoryDTO categoryDTO, Long id);

    List<CategoryDTO> getAll();

    List<SubCategoryDTO> getSubCategories(Long categoryId);
}