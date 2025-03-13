package com.pragma.hogar360_microservice_house.domain.usecases;

import com.pragma.hogar360_microservice_house.domain.exceptions.DepartmentAlreadyExistsException;
import com.pragma.hogar360_microservice_house.domain.model.CityModel;
import com.pragma.hogar360_microservice_house.domain.model.DepartmentModel;
import com.pragma.hogar360_microservice_house.domain.ports.in.ILocationServicePort;
import com.pragma.hogar360_microservice_house.domain.ports.out.ILocationPersistencePort;

public class LocationUseCase implements ILocationServicePort {

    private final ILocationPersistencePort locationPersistencePort;

    public LocationUseCase(ILocationPersistencePort locationPersistencePort) {
        this.locationPersistencePort = locationPersistencePort;
    }

    @Override
    public void save(CityModel cityModel, DepartmentModel departmentModel) {
        if (locationPersistencePort.findDepartmentByName(departmentModel.getName()).isPresent()){
            throw new DepartmentAlreadyExistsException();
        }
        locationPersistencePort.saveDepartment(departmentModel);

        if (locationPersistencePort.findCityByName(cityModel.getName()).isEmpty()){
            locationPersistencePort.saveCity(cityModel);
        }
    }
}
