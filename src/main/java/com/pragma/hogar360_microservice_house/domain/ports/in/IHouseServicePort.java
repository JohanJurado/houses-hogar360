package com.pragma.hogar360_microservice_house.domain.ports.in;

import com.pragma.hogar360_microservice_house.domain.model.HouseModel;
import com.pragma.hogar360_microservice_house.domain.model.filters.HouseFilterModel;
import com.pragma.hogar360_microservice_house.domain.util.pagination.Pagination;

public interface IHouseServicePort {

    void publish(HouseModel houseModel);
    Pagination<HouseModel> getHouses(
            HouseFilterModel filterModel,
            Integer page,
            Integer size,
            String orderBy,
            boolean orderAsc
    );
}
