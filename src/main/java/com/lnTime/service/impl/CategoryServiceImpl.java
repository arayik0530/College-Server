package com.lnTime.service.impl;

import com.lnTime.domain.CategoryEntity;
import com.lnTime.dto.category.CategoryDTO;
import com.lnTime.dto.subCategory.SubCategoryDTO;
import com.lnTime.repository.CategoryRepository;
import com.lnTime.repository.SubCategoryRepository;
import com.lnTime.service.CategoryService;
import com.lnTime.service.util.exception.CategoryNotFoundException;
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
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Override
    public CategoryDTO findById(Long id) {
        Optional<CategoryEntity> byId = categoryRepository.findById(id);
        if (byId.isPresent()) {
            return CategoryDTO.mapFromEntity(byId.get());
        } else {
            throw new CategoryNotFoundException(id);
        }
    }

    @Override
    @Transactional
    public void remove(Long id) {
        Optional<CategoryEntity> byId = categoryRepository.findById(id);
        if (byId.isPresent()) {
            categoryRepository.delete(byId.get());
        }
    }

    @Override
    @Transactional
    public void save(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryRepository.save(categoryDTO.toEntity(categoryEntity));
    }

    @Override
    @Transactional
    public void update(CategoryDTO categoryDTO, Long id) {
        Optional<CategoryEntity> byId = categoryRepository.findById(id);
        if (byId.isPresent()) {
            CategoryEntity categoryEntity = byId.get();
            categoryDTO.toEntity(categoryEntity);
            categoryRepository.save(categoryEntity);
        } else {
            throw new CategoryNotFoundException(id);
        }
    }

    @Override
    public List<CategoryDTO> getAll() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        return categoryEntities
                .stream()
                .map(c -> CategoryDTO.mapFromEntity(c))
                .collect(Collectors.toList());
    }

    @Override
    public List<SubCategoryDTO> getSubCategories(Long categoryId) {
        CategoryEntity categoryEntity = new CategoryEntity();
        return subCategoryRepository
                .findAllByCategory(this.findById(categoryId)
                        .toEntity(categoryEntity))
                .stream()
                .map(s -> SubCategoryDTO.mapFromEntity(s))
                .collect(Collectors.toList());
    }
}
