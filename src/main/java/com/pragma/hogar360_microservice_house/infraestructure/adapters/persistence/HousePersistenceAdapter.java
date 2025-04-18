package com.pragma.hogar360_microservice_house.infraestructure.adapters.persistence;

import com.pragma.hogar360_microservice_house.domain.model.HouseModel;
import com.pragma.hogar360_microservice_house.domain.model.filters.HouseFilterModel;
import com.pragma.hogar360_microservice_house.domain.ports.out.IHousePersistencePort;
import com.pragma.hogar360_microservice_house.infraestructure.mappers.IHouseEntityMapper;
import com.pragma.hogar360_microservice_house.infraestructure.repositories.mysql.IHouseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<HouseModel> findById(Long idHouse) {
        return houseEntityMapper.entityOptionalToModelOptional(houseRepository.findById(idHouse));
    }

    @Override
    public List<HouseModel> findHousesByFilters(HouseFilterModel filterModel, String publicationStatus) {
        return houseEntityMapper.entityListToModelList(
                houseRepository.findHousesByFilters(filterModel, publicationStatus)
        );
    }

    @Override
    public List<HouseModel> findByPublicationStatusAndActivePublicationDate(String status, LocalDate date) {
        return houseEntityMapper.entityListToModelList(houseRepository.findByPublicationStatusAndActivePublicationDate(status, date));
    }
}
