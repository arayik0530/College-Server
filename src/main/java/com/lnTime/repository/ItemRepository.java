package com.lnTime.repository;

import com.lnTime.domain.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    @Query(value = "from ItemEntity as item where item.title like ?1% " +
            "OR item.description like %?1%")
    Page<ItemEntity> searchByParam(String param, Pageable pageable);
}