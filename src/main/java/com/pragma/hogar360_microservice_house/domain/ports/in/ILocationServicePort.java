package com.pragma.hogar360_microservice_house.domain.ports.in;

import com.pragma.hogar360_microservice_house.domain.model.CityModel;
import com.pragma.hogar360_microservice_house.domain.model.DepartmentModel;

public interface ILocationServicePort {
    void save(CityModel city, DepartmentModel departmentModel);
}
