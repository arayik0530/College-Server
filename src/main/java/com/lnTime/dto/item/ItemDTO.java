package com.lnTime.dto.item;

import com.lnTime.domain.ItemEntity;
import com.lnTime.domain.UserEntity;
import com.lnTime.dto.user.UserInfoDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.stream.Stream;

@Data
public class ItemDTO {

    private Long id;

    private String title;

    private String description;

    private String path;

    private String text;

    private LocalDateTime created;

    private LocalDateTime lastUpdated;

    public static ItemDTO mapFromEntity(ItemEntity item) {

        if (item == null) {
            return null;
        }

        ItemDTO itemDTO = new ItemDTO();

        itemDTO.title = item.getTitle();
        itemDTO.id = item.getId();
        itemDTO.description = item.getDescription();
        itemDTO.path = item.getPath();
        itemDTO.text = item.getText();
        itemDTO.created = item.getCreated();
        itemDTO.lastUpdated = item.getLastUpdated();

        return itemDTO;
    }

    public ItemEntity toEntity( ItemEntity item) {

        item.setId(this.id);
        item.setTitle(this.title);
        item.setDescription(this.description);
        item.setPath(this.path);
        item.setText(this.text);

        return item;
    }
}
