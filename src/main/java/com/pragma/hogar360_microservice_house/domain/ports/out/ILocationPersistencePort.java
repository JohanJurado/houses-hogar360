package com.pragma.hogar360_microservice_house.domain.ports.out;

import com.pragma.hogar360_microservice_house.domain.model.CityModel;
import com.pragma.hogar360_microservice_house.domain.model.DepartmentModel;

import java.util.List;
import java.util.Optional;

public interface ILocationPersistencePort {
    DepartmentModel saveDepartment(DepartmentModel departmentModel);
    Optional<DepartmentModel> findDepartmentByName(String nameDepartment);
    List<DepartmentModel> getAllDepartments();

    void saveCity(CityModel cityModel);
    Optional<List<CityModel>> findCityByName(String nameCity);
    List<CityModel> getAllCities();
    List<CityModel> findAllByDepartmentName(String nameDepartment);



}

