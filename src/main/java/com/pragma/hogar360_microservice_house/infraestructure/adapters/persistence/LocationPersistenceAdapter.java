package com.pragma.hogar360_microservice_house.infraestructure.adapters.persistence;

import com.pragma.hogar360_microservice_house.domain.model.LocationModel;
import com.pragma.hogar360_microservice_house.domain.ports.out.ILocationPersistencePort;
import com.pragma.hogar360_microservice_house.infraestructure.mappers.ILocationEntityMapper;
import com.pragma.hogar360_microservice_house.infraestructure.repositories.mysql.ILocationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class LocationPersistenceAdapter implements ILocationPersistencePort {

    private final ILocationRepository locationRepository;
    private final ILocationEntityMapper locationEntityMapper;

    @Override
    public void save(LocationModel locationModel) {
        locationRepository.save(locationEntityMapper.modelToEntity(locationModel));
    }

    @Override
    public Optional<LocationModel> findByNeighborhoodAndCityId(String neighborhood, Long cityId) {
        return locationEntityMapper.entityOptionalToModelOptional(locationRepository.findByNeighborhoodAndCityEntityId(neighborhood, cityId));
    }

    @Override
    public Optional<LocationModel> findByNeighborhoodAndCityNameAndDepartmentName(String neighborhood, String nameCity, String nameDepartment) {
        return locationEntityMapper.entityOptionalToModelOptional(
                locationRepository.findByNeighborhoodAndCityEntityNameAndCityEntityDepartmentEntityName(neighborhood, nameCity, nameDepartment)
        );
    }

    @Override
    public List<LocationModel> findAllByCityOrDepartment(String nameLocation) {
        return locationEntityMapper.entityListToModelList(locationRepository.findByCityOrDepartment(nameLocation));
    }
}
