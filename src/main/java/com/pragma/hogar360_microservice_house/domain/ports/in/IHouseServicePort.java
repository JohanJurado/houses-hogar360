package com.pragma.hogar360_microservice_house.domain.ports.in;

import com.pragma.hogar360_microservice_house.domain.model.HouseModel;
import com.pragma.hogar360_microservice_house.domain.util.pagination.Pagination;

public interface IHouseServicePort {

    void publish(HouseModel houseModel);
    Pagination<HouseModel> getHouses(
            String nameCity,
            String nameDepartment,
            String nameCategory,
            Long bedroomCount,
            Long bathroomCount,
            Double minPrice,
            Double maxPrice,
            Integer page,
            Integer size,
            String orderBy,
            boolean orderAsc
    );
}
