package com.pragma.hogar360_microservice_house.application.services;

import com.pragma.hogar360_microservice_house.application.dtos.request.SaveLocationRequest;
import com.pragma.hogar360_microservice_house.application.dtos.response.SaveDtoResponses;

public interface ILocationService {
    SaveDtoResponses save(SaveLocationRequest saveLocationRequest);
}
