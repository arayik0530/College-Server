package com.lnTime.dto.subCategory;

import com.lnTime.domain.CategoryEntity;
import com.lnTime.domain.SubCategoryEntity;
import com.lnTime.repository.CategoryRepository;
import com.lnTime.service.util.exception.CategoryNotFoundException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Data
public class SubCategoryDTO {

    @Autowired
    private CategoryRepository categoryRepository;

    private Long categoryId;

    private Long id;

    private String title;

    public static SubCategoryDTO mapFromEntity(SubCategoryEntity subCategory) {

        if (subCategory == null) {
            return null;
        }

        SubCategoryDTO subCategoryDTO = new SubCategoryDTO();

        subCategoryDTO.title = subCategory.getTitle();
        subCategoryDTO.id = subCategory.getId();
        subCategoryDTO.categoryId = subCategory.getCategory().getId();

        return subCategoryDTO;
    }

    public SubCategoryEntity toEntity(SubCategoryEntity subCategory) {

        subCategory.setId(this.id);
        Optional<CategoryEntity> byId = categoryRepository.findById(this.categoryId);
        if(byId.isPresent())
            subCategory.setCategory(byId.get());
        else
            throw new CategoryNotFoundException(this.categoryId);
        subCategory.setTitle(this.title);

        return subCategory;
    }
}