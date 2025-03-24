package com.pragma.hogar360_microservice_house.infraestructure.adapters.persistence;

import com.pragma.hogar360_microservice_house.domain.model.HouseModel;
import com.pragma.hogar360_microservice_house.domain.ports.out.IHousePersistencePort;
import com.pragma.hogar360_microservice_house.infraestructure.mappers.IHouseEntityMapper;
import com.pragma.hogar360_microservice_house.infraestructure.repositories.mysql.IHouseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class HousePersistenceAdapter implements IHousePersistencePort {

    private final IHouseEntityMapper houseEntityMapper;
    private final IHouseRepository houseRepository;

    @Override
    public void save(HouseModel houseModel) {
        houseRepository.save(houseEntityMapper.modelToEntity(houseModel));
    }
}
