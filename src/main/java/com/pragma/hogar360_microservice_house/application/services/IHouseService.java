package com.pragma.hogar360_microservice_house.application.services;

import com.pragma.hogar360_microservice_house.application.dtos.request.SaveHouseRequest;
import com.pragma.hogar360_microservice_house.application.dtos.request.filters.HouseFilterRequest;
import com.pragma.hogar360_microservice_house.application.dtos.response.HouseResponse;
import com.pragma.hogar360_microservice_house.application.dtos.response.SaveDtoResponses;
import com.pragma.hogar360_microservice_house.domain.util.pagination.Pagination;

public interface IHouseService {

   SaveDtoResponses publish(SaveHouseRequest saveHouseRequest);
   Pagination<HouseResponse> getHouses(HouseFilterRequest filterRequest, Integer page, Integer size,
                                       String orderBy, boolean orderAsc);
}
