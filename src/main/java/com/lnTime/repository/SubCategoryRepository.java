package com.lnTime.repository;

import com.lnTime.domain.CategoryEntity;
import com.lnTime.domain.SubCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategoryEntity, Long> {
    List<SubCategoryEntity> findAllByCategory(CategoryEntity categoryEntity);
}