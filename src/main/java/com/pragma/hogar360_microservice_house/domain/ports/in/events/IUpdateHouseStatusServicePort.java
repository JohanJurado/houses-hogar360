package com.pragma.hogar360_microservice_house.domain.ports.in.events;

public interface IUpdateHouseStatusServicePort {
    void updateHouseStatusIfNeeded(Long houseId);
}
