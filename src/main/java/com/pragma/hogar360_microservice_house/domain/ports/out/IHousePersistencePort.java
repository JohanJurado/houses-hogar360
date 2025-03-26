package com.pragma.hogar360_microservice_house.domain.ports.out;

import com.pragma.hogar360_microservice_house.domain.model.HouseModel;

import java.util.List;

public interface IHousePersistencePort {

    void save(HouseModel houseModel);
    List<HouseModel> findHousesByFilters(
            String nameCity,
            String nameDepartment,
            String nameCategory,
            Long bedroomCount,
            Long bathroomCount,
            Double minPrice,
            Double maxPrice
    );
}
