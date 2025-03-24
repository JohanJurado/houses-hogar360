package com.pragma.hogar360_microservice_house.application.services;

import com.pragma.hogar360_microservice_house.application.dtos.request.SaveHouseRequest;
import com.pragma.hogar360_microservice_house.application.dtos.response.SaveDtoResponses;

public interface IHouseService {

   SaveDtoResponses publish(SaveHouseRequest saveHouseRequest);
}
