package com.pragma.hogar360_microservice_house.domain.ports.in;

import com.pragma.hogar360_microservice_house.domain.model.CityModel;
import com.pragma.hogar360_microservice_house.domain.model.DepartmentModel;
import com.pragma.hogar360_microservice_house.domain.model.LocationModel;
import com.pragma.hogar360_microservice_house.domain.util.pagination.Pagination;

public interface ILocationServicePort {
    void save(LocationModel locationModel);
    Pagination<LocationModel> getLocations(String nameLocation, Integer page, Integer size, String orderBy, boolean orderAsc);
}
