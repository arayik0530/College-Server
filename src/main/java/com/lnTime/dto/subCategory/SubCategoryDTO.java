package com.lnTime.dto.subCategory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lnTime.domain.CategoryEntity;
import com.lnTime.domain.SubCategoryEntity;
import com.lnTime.repository.CategoryRepository;
import com.lnTime.service.util.exception.CategoryNotFoundException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Data
public class SubCategoryDTO {


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

        subCategory.setTitle(this.title);
        subCategory.setId(this.id);
        return subCategory;
    }
}