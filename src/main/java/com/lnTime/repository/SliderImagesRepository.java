package com.lnTime.repository;

import com.lnTime.domain.SliderImagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SliderImagesRepository extends JpaRepository<SliderImagesEntity, Integer> {
}