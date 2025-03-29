package com.pragma.hogar360_microservice_house.infraestructure.repositories.mysql;

import com.pragma.hogar360_microservice_house.infraestructure.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByName(String name);

    @Query("SELECT c FROM CategoryEntity c " +
            "WHERE (:name IS NULL OR c.name LIKE CONCAT('%', :name, '%'))")
    List<CategoryEntity> findAllByNameContaining(@Param("name") String name);
}
