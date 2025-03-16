package com.pragma.hogar360_microservice_house.domain.ports.in;

import com.pragma.hogar360_microservice_house.domain.model.CityModel;
import com.pragma.hogar360_microservice_house.domain.model.DepartmentModel;
import com.pragma.hogar360_microservice_house.domain.util.pagination.Pagination;

public interface ILocationServicePort {
    void save(CityModel city, DepartmentModel departmentModel);
    Pagination<DepartmentModel> getLocations(String nameLocation, Integer page, Integer size, String orderBy, boolean orderAsc);
}
