package com.lnTime.dto.category;

import com.lnTime.domain.CategoryEntity;
import lombok.Data;

@Data
public class CategoryDTO {

    private Long id;

    private String title;

    public static CategoryDTO mapFromEntity(CategoryEntity category) {

        if (category == null) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.title = category.getTitle();
        categoryDTO.id = category.getId();

        return categoryDTO;
    }

    public CategoryEntity toEntity(CategoryEntity category) {

        category.setId(this.id);
        category.setTitle(this.title);

        return category;
    }
}