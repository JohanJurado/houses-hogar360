package com.pragma.hogar360_microservice_house.infraestructure.adapters.persistence;

import com.pragma.hogar360_microservice_house.domain.model.CityModel;
import com.pragma.hogar360_microservice_house.domain.model.DepartmentModel;
import com.pragma.hogar360_microservice_house.domain.ports.out.ILocationPersistencePort;
import com.pragma.hogar360_microservice_house.infraestructure.entities.DepartmentEntity;
import com.pragma.hogar360_microservice_house.infraestructure.mappers.ILocationEntityMapper;
import com.pragma.hogar360_microservice_house.infraestructure.repositories.mysql.ICityRepository;
import com.pragma.hogar360_microservice_house.infraestructure.repositories.mysql.IDepartmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LocationPersistenceAdapter implements ILocationPersistencePort {

    private final ICityRepository cityRepository;
    private final IDepartmentRepository departmentRepository;
    private final ILocationEntityMapper locationEntityMapper;

    @Override
    public CityModel saveCity(CityModel cityModel) {
        return locationEntityMapper.entityToModelCity(cityRepository.save(locationEntityMapper.modelToEntityCity(cityModel)));
    }

    @Override
    public Optional<CityModel> findCityByName(String nameCity) {
        return locationEntityMapper.entityOptionalToModelOptionalCity(cityRepository.findByName(nameCity));
    }

    @Override
    public List<CityModel> getAllCities() {
        return locationEntityMapper.entityListToModelListCity(cityRepository.findAll());
    }

    @Override
    public void saveDepartment(DepartmentModel departmentModel) {
        departmentRepository.save(locationEntityMapper.modelToEntityDepartment(departmentModel));
    }

    @Override
    public Optional<DepartmentModel> findDepartmentByName(String nameDepartment) {
        return locationEntityMapper.entityOptionalToModelOptionalDepartment(departmentRepository.findByName(nameDepartment));
    }

    @Override
    public List<DepartmentModel> getAllDepartments() {
        List<DepartmentEntity> departmentEntityList = departmentRepository.findAll();
        return departmentEntityList.stream()
                .map(locationEntityMapper::entityToModelDepartment)
                .toList();
    }

    @Override
    public List<DepartmentModel> findAllByCityName(String nameCity) {
        List<DepartmentEntity> departmentEntityList = departmentRepository.findByCityName(nameCity);
        return departmentEntityList.stream()
                .map(locationEntityMapper::entityToModelDepartment)
                .toList();
    }
}
