package com.pragma.hogar360_microservice_house.infraestructure.repositories.mysql;

import com.pragma.hogar360_microservice_house.infraestructure.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IDepartmentRepository extends JpaRepository<DepartmentEntity, Long> {

    Optional<DepartmentEntity> findByName(String nameDepartment);

    // -----
    @Query("SELECT d FROM DepartmentEntity d WHERE d.name LIKE CONCAT('%', :nameDepartment, '%')")
    List<DepartmentEntity> findByMatches(@Param("nameDepartment") String nameDepartment);
}
