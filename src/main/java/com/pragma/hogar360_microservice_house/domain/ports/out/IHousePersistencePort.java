package com.pragma.hogar360_microservice_house.domain.ports.out;

import com.pragma.hogar360_microservice_house.domain.model.HouseModel;
import com.pragma.hogar360_microservice_house.domain.model.filters.HouseFilterModel;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IHousePersistencePort {

    void save(HouseModel houseModel);
    Optional<HouseModel> findById(Long idHouse);

    List<HouseModel> findHousesByFilters(HouseFilterModel filterModel, String publicationStatus);
    List<HouseModel> findByPublicationStatusAndActivePublicationDate(String status, LocalDate date);
}
