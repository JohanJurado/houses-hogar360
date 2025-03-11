package com.pragma.hogar360_microservice_house.category.infraestructure.repositories.mysql;

import com.pragma.hogar360_microservice_house.category.infraestructure.entities.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity findByName(String name);
    Page<CategoryEntity> findAll(Pageable pageable);
}
