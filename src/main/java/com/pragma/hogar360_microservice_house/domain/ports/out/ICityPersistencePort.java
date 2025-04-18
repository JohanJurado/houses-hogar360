package com.pragma.hogar360_microservice_house.domain.ports.out;

import com.pragma.hogar360_microservice_house.domain.model.CityModel;

import java.util.Optional;

public interface ICityPersistencePort {
    CityModel save(CityModel cityModel);
    Optional<CityModel> findByNameAndDepartmentId(String nameCity, Long departmentId);

}
