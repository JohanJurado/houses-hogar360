package com.pragma.hogar360_microservice_house.infraestructure.repositories.mysql;

import com.pragma.hogar360_microservice_house.infraestructure.entities.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ILocationRepository extends JpaRepository<LocationEntity, Long> {

    Optional<LocationEntity> findByNeighborhoodAndCityEntityId(String neighborhood, Long cityId);
    List<LocationEntity> findAllByCityEntityDepartmentEntityName(String departmentName);
    List<LocationEntity> findAllByCityEntityName(String cityName);
}
