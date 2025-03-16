package com.pragma.hogar360_microservice_house.domain.ports.out;

import com.pragma.hogar360_microservice_house.domain.model.CityModel;
import com.pragma.hogar360_microservice_house.domain.model.DepartmentModel;

import java.util.List;
import java.util.Optional;

public interface ILocationPersistencePort {
    CityModel saveCity(CityModel cityModel);
    Optional<CityModel> findCityByName(String nameCity);
    List<CityModel> getAllCities();

    void saveDepartment(DepartmentModel departmentModel);
    Optional<DepartmentModel> findDepartmentByName(String nameDepartment);
    List<DepartmentModel> getAllDepartments();
    List<DepartmentModel> findAllByCityName(String nameCity);



}

