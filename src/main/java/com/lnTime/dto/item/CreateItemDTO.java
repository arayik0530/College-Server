package com.lnTime.dto.item;

import com.lnTime.domain.ItemEntity;
import lombok.Data;

@Data
public class CreateItemDTO {

    private String title;

    private String description;

    private String text;

    private Long subCategoryId;

    public static CreateItemDTO mapFromEntity(ItemEntity item) {

        if (item == null) {
            return null;
        }

        CreateItemDTO createItemDTO = new CreateItemDTO();

        createItemDTO.title = item.getTitle();
        createItemDTO.description = item.getDescription();
        createItemDTO.text = item.getText();
        createItemDTO.subCategoryId = item.getSubCategory().getId();

        return createItemDTO;
    }

    public ItemEntity toEntity( ItemEntity item) {

        item.setTitle(this.title);
        item.setDescription(this.description);
        item.setText(this.text);

        return item;
    }

}
