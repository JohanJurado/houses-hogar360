package com.pragma.hogar360_microservice_house.application.services.impl;

import com.pragma.hogar360_microservice_house.application.dtos.request.SaveHouseRequest;
import com.pragma.hogar360_microservice_house.application.dtos.response.SaveDtoResponses;
import com.pragma.hogar360_microservice_house.application.mappers.IHouseDtoMapper;
import com.pragma.hogar360_microservice_house.application.services.IHouseService;
import com.pragma.hogar360_microservice_house.application.utils.ApplicationConstants;
import com.pragma.hogar360_microservice_house.domain.ports.in.IHouseServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements IHouseService {

    private final IHouseServicePort houseServicePort;
    private final IHouseDtoMapper houseDtoMapper;

    @Override
    public SaveDtoResponses publish(SaveHouseRequest saveHouseRequest) {
        houseServicePort.publish(houseDtoMapper.requestToModel(saveHouseRequest));
        return new SaveDtoResponses(ApplicationConstants.SAVE_HOUSE_RESPONSE_MESSAGE, LocalDateTime.now());
    }
}
