package com.lnTime.dto.item;

import com.lnTime.domain.ItemEntity;
import com.lnTime.domain.SubCategoryEntity;
import com.lnTime.repository.SubCategoryRepository;
import com.lnTime.service.util.exception.SubCategoryNotFoundException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
public class ItemDTO {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    private Long id;

    private String title;

    private String description;

    private String path;

    private String text;

    private LocalDateTime created;

    private LocalDateTime lastUpdated;

    private Long subCategoryId;

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
        itemDTO.subCategoryId = item.getSubCategory().getId();

        return itemDTO;
    }

    public ItemEntity toEntity( ItemEntity item) {

        item.setId(this.id);
        item.setTitle(this.title);
        item.setDescription(this.description);
        item.setPath(this.path);
        item.setText(this.text);
        Optional<SubCategoryEntity> byId = subCategoryRepository.findById(this.subCategoryId);
        if(byId.isPresent())
            item.setSubCategory(byId.get());
        else
            throw new SubCategoryNotFoundException(subCategoryId);

        return item;
    }
}
