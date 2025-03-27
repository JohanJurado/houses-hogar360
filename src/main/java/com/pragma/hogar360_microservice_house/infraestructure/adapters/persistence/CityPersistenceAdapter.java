package com.pragma.hogar360_microservice_house.infraestructure.adapters.persistence;

import com.pragma.hogar360_microservice_house.domain.model.CityModel;
import com.pragma.hogar360_microservice_house.domain.ports.out.ICityPersistencePort;
import com.pragma.hogar360_microservice_house.infraestructure.mappers.ICityEntityMapper;
import com.pragma.hogar360_microservice_house.infraestructure.repositories.mysql.ICityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CityPersistenceAdapter implements ICityPersistencePort {

    private final ICityRepository cityRepository;
    private final ICityEntityMapper cityEntityMapper;

    @Override
    public CityModel save(CityModel cityModel) {
        return cityEntityMapper.entityToModel(cityRepository.save(cityEntityMapper.modelToEntity(cityModel)));
    }

    @Override
    public Optional<CityModel> findByNameAndDepartmentId(String nameCity, Long departmentId) {
        return cityEntityMapper.entityOptionalToModelOptional(cityRepository.findByNameAndDepartmentEntityId(nameCity, departmentId));
    }
}
