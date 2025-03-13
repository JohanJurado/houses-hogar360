package com.pragma.hogar360_microservice_house.category.infraestructure.repositories.mysql;

import com.pragma.hogar360_microservice_house.category.infraestructure.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByName(String name);
}
