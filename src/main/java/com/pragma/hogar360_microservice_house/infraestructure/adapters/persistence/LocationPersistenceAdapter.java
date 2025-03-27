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
    public List<LocationModel> getAllLocations() {
        return locationEntityMapper.entityListToModelList(locationRepository.findAll());
    }

    @Override
    public List<LocationModel> findAllByDepartmentName(String departmentName) {
        return locationEntityMapper.entityListToModelList(locationRepository.findAllByCityEntityDepartmentEntityName(departmentName));
    }

    @Override
    public List<LocationModel> findAllByCityName(String cityName) {
        return locationEntityMapper.entityListToModelList(locationRepository.findAllByCityEntityName(cityName));
    }
}
