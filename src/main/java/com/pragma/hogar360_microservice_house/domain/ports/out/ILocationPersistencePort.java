package com.pragma.hogar360_microservice_house.domain.ports.out;

import com.pragma.hogar360_microservice_house.domain.model.CityModel;
import com.pragma.hogar360_microservice_house.domain.model.DepartmentModel;

import java.util.Optional;

public interface ILocationPersistencePort {
    void saveCity(CityModel cityModel);
    Optional<CityModel> findCityByName(String nameCity);

    void saveDepartment(DepartmentModel departmentModel);
    Optional<DepartmentModel> findDepartmentByName(String nameDepartment);
}

