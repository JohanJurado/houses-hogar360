package com.pragma.hogar360_microservice_house.application.services;

import com.pragma.hogar360_microservice_house.application.dtos.request.SaveLocationRequest;
import com.pragma.hogar360_microservice_house.application.dtos.response.LocationResponse;
import com.pragma.hogar360_microservice_house.application.dtos.response.SaveDtoResponses;
import com.pragma.hogar360_microservice_house.domain.util.pagination.Pagination;

public interface ILocationService {
    SaveDtoResponses save(SaveLocationRequest saveLocationRequest);
    Pagination<LocationResponse> getLocations(String nameLocation, Integer page, Integer size, String orderBy, boolean orderAsc);
}
