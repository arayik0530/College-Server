package com.lnTime.dto.category;

import com.lnTime.domain.CategoryEntity;
import lombok.Data;

@Data
public class CreateCategoryDTO {

    private String title;

    public static CreateCategoryDTO mapFromEntity(CategoryEntity category) {

        if (category == null) {
            return null;
        }

        CreateCategoryDTO createCategoryDTO = new CreateCategoryDTO();

        createCategoryDTO.title = category.getTitle();

        return createCategoryDTO;
    }

    public CategoryEntity toEntity(CategoryEntity category) {

        category.setTitle(this.title);

        return category;
    }
}