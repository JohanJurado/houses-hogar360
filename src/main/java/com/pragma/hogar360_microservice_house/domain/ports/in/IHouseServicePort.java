package com.pragma.hogar360_microservice_house.domain.ports.in;

import com.pragma.hogar360_microservice_house.domain.model.HouseModel;

public interface IHouseServicePort {

    void publish(HouseModel houseModel);
}
