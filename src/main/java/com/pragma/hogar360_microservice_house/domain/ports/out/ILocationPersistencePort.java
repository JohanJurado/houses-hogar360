package com.pragma.hogar360_microservice_house.domain.ports.out;

import com.pragma.hogar360_microservice_house.domain.model.LocationModel;

import java.util.List;
import java.util.Optional;

public interface ILocationPersistencePort {

    void save(LocationModel locationModel);
    Optional<LocationModel> findByNeighborhoodAndCityId(String neighborhood, Long cityId);

    List<LocationModel> getAllLocations();
    List<LocationModel> findAllByDepartmentName(String departmentName);
    List<LocationModel> findAllByCityName(String cityName);
}

