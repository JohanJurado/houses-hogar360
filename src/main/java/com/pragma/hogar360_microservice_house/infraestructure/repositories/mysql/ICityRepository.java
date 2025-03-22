package com.pragma.hogar360_microservice_house.infraestructure.repositories.mysql;

import com.pragma.hogar360_microservice_house.infraestructure.entities.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ICityRepository extends JpaRepository<CityEntity, Long> {
    Optional<CityEntity> findByName(String nameCity);
    List<CityEntity> findByDepartmentEntityName(String nameDepartment);

}
