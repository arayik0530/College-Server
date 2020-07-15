package com.lnTime.service.impl;

import com.lnTime.domain.CategoryEntity;
import com.lnTime.domain.SubCategoryEntity;
import com.lnTime.dto.item.ItemDTO;
import com.lnTime.dto.subCategory.CreateSubCategoryDTO;
import com.lnTime.dto.subCategory.SubCategoryDTO;
import com.lnTime.repository.CategoryRepository;
import com.lnTime.repository.ItemRepository;
import com.lnTime.repository.SubCategoryRepository;
import com.lnTime.service.SubCategoryService;
import com.lnTime.service.util.exception.CategoryNotFoundException;
import com.lnTime.service.util.exception.SubCategoryNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {
    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public SubCategoryDTO findById(Long id) {
        Optional<SubCategoryEntity> byId = subCategoryRepository.findById(id);
        if (byId.isPresent()) {
            return SubCategoryDTO.mapFromEntity(byId.get());
        } else {
            throw new SubCategoryNotFoundException(id);
        }
    }

    @Override
    @Transactional
    public void remove(Long id) {
        Optional<SubCategoryEntity> byId = subCategoryRepository.findById(id);
        if (byId.isPresent()) {
            subCategoryRepository.delete(byId.get());
        }
    }

    @Override
    @Transactional
    public void save(CreateSubCategoryDTO createSubCategoryDTO) {
        SubCategoryEntity subCategoryEntity = new SubCategoryEntity();
        Optional<CategoryEntity> byId = categoryRepository.findById(createSubCategoryDTO.getCategoryId());
        if(byId.isPresent())
            subCategoryEntity.setCategory(byId.get());
        else
            throw new CategoryNotFoundException(createSubCategoryDTO.getCategoryId());

        subCategoryRepository.save(createSubCategoryDTO.toEntity(subCategoryEntity));
    }

    @Override
    @Transactional
    public void update(SubCategoryDTO subCategoryDTO, Long id) {
        Optional<SubCategoryEntity> byId = subCategoryRepository.findById(id);
        if (byId.isPresent()) {
            SubCategoryEntity subCategoryEntity = byId.get();
            subCategoryDTO.toEntity(subCategoryEntity);
            subCategoryRepository.save(subCategoryEntity);
        } else {
            throw new CategoryNotFoundException(id);
        }
    }

    @Override
    public List<SubCategoryDTO> getAll() {
        return subCategoryRepository
                .findAll()
                .stream()
                .map(s -> SubCategoryDTO.mapFromEntity(s))
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemDTO> getItems(Long subCategoryId) {
        SubCategoryEntity subCategoryEntity = new SubCategoryEntity();
        return itemRepository
                .findAllBySubCategory(this.findById(subCategoryId).toEntity(subCategoryEntity))
                .stream()
                .map(ItemDTO::mapFromEntity)
                .collect(Collectors.toList());
    }
}
