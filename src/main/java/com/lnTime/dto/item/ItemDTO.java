package com.lnTime.dto.item;

import com.lnTime.domain.ItemEntity;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ItemDTO {

    private Long id;

    private String title;

    private String description;

    private String text;

    private LocalDateTime created;

    private LocalDateTime lastUpdated;

    private Long subCategoryId;

    private List<String> imageURLs = new ArrayList<>();

    public static ItemDTO mapFromEntity(ItemEntity item) {

        if (item == null) {
            return null;
        }

        ItemDTO itemDTO = new ItemDTO();

        itemDTO.title = item.getTitle();
        itemDTO.id = item.getId();
        itemDTO.description = item.getDescription();
        itemDTO.text = item.getText();
        itemDTO.created = item.getCreated();
        itemDTO.lastUpdated = item.getLastUpdated();
        itemDTO.subCategoryId = item.getSubCategory().getId();

        return itemDTO;
    }

    public ItemEntity toEntity( ItemEntity item) {

        item.setTitle(this.title);
        item.setDescription(this.description);
        item.setText(this.text);

        return item;
    }
}
