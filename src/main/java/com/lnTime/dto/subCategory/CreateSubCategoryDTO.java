package com.lnTime.dto.subCategory;

import com.lnTime.domain.SubCategoryEntity;
import lombok.Data;

@Data
public class CreateSubCategoryDTO {

    private Long categoryId;

    private String title;

    public static CreateSubCategoryDTO mapFromEntity(SubCategoryEntity subCategory) {

        if (subCategory == null) {
            return null;
        }

        CreateSubCategoryDTO createSubCategoryDTO = new CreateSubCategoryDTO();

        createSubCategoryDTO.title = subCategory.getTitle();
        createSubCategoryDTO.categoryId = subCategory.getCategory().getId();

        return createSubCategoryDTO;
    }

    public SubCategoryEntity toEntity(SubCategoryEntity subCategory) {

        subCategory.setTitle(this.title);

        return subCategory;
    }
}
