package com.pragma.hogar360_microservice_house.infraestructure.repositories.mysql;

import com.pragma.hogar360_microservice_house.infraestructure.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IDepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
    Optional<DepartmentEntity> findByName(String nameDepartment);
    List<DepartmentEntity> findByCityName(String nameCity);
}
