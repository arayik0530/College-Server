package com.lnTime.repository;

import com.lnTime.domain.ItemEntity;
import com.lnTime.domain.SubCategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    @Query(value = "from ItemEntity as item where item.title like concat(?1, '%') " +
            "OR item.description like concat(concat('%', ?1), '%')")
    Page<ItemEntity> searchByParam(String param, Pageable pageable);

    List<ItemEntity> findAllBySubCategory(SubCategoryEntity subCategoryEntity);

    Page<ItemEntity> findAllByOrderByCreatedDesc(Pageable pageable);

}