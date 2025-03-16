package com.pragma.hogar360_microservice_house.application.services.impl;

import com.pragma.hogar360_microservice_house.application.dtos.request.SaveLocationRequest;
import com.pragma.hogar360_microservice_house.application.dtos.response.LocationResponse;
import com.pragma.hogar360_microservice_house.application.dtos.response.SaveDtoResponses;
import com.pragma.hogar360_microservice_house.application.mappers.ILocationDtoMapper;
import com.pragma.hogar360_microservice_house.application.services.ILocationService;
import com.pragma.hogar360_microservice_house.application.utils.ApplicationConstants;
import com.pragma.hogar360_microservice_house.domain.model.CityModel;
import com.pragma.hogar360_microservice_house.domain.model.DepartmentModel;
import com.pragma.hogar360_microservice_house.domain.ports.in.ILocationServicePort;
import com.pragma.hogar360_microservice_house.domain.util.pagination.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements ILocationService {

    private final ILocationServicePort locationServicePort;
    private final ILocationDtoMapper locationDtoMapper;

    @Override
    public SaveDtoResponses save(SaveLocationRequest saveLocationRequest) {
        CityModel cityModel = locationDtoMapper.requestToModelCity(saveLocationRequest);
        DepartmentModel departmentModel = locationDtoMapper.requestToModelDepartment(saveLocationRequest);
        locationServicePort.save(cityModel, departmentModel);
        return new SaveDtoResponses(ApplicationConstants.SAVE_LOCATION_RESPONSE_MESSAGE, LocalDateTime.now());
    }

    @Override
    public Pagination<LocationResponse> getLocations(String nameLocation, Integer page, Integer size, String orderBy, boolean orderAsc) {
        return locationDtoMapper.modelToResponse(locationServicePort.getLocations(nameLocation, page, size, orderBy, orderAsc));
    }
}
