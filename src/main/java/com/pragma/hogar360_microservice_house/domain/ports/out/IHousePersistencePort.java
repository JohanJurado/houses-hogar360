package com.pragma.hogar360_microservice_house.domain.ports.out;

import com.pragma.hogar360_microservice_house.domain.model.HouseModel;

public interface IHousePersistencePort {

    void save(HouseModel houseModel);
}
