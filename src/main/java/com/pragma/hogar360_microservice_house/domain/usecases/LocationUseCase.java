package com.pragma.hogar360_microservice_house.domain.usecases;

import com.pragma.hogar360_microservice_house.domain.exceptions.*;
import com.pragma.hogar360_microservice_house.domain.model.CityModel;
import com.pragma.hogar360_microservice_house.domain.model.DepartmentModel;
import com.pragma.hogar360_microservice_house.domain.ports.in.ILocationServicePort;
import com.pragma.hogar360_microservice_house.domain.ports.out.ILocationPersistencePort;
import com.pragma.hogar360_microservice_house.domain.util.constants.DomainConstants;
import com.pragma.hogar360_microservice_house.domain.util.pagination.Pagination;
import com.pragma.hogar360_microservice_house.domain.util.pagination.PaginationConstants;
import com.pragma.hogar360_microservice_house.domain.util.validations.Validations;

import java.util.*;

public class LocationUseCase implements ILocationServicePort {

    private final ILocationPersistencePort locationPersistencePort;

    public LocationUseCase(ILocationPersistencePort locationPersistencePort) {
        this.locationPersistencePort = locationPersistencePort;
    }

    @Override
    public void save(CityModel cityModel, DepartmentModel departmentModel) {
        Validations.validationByAttributeIsNullOrBlank(cityModel.getName(), new LocationCityNameCannotBeEmptyException());
        Validations.validationByLimitCharacters(cityModel.getName(), DomainConstants.MAX_NAME_SIZE_LOCATION, new LocationNameMaxSizeExceedException());
        Validations.validationByAttributeIsNullOrBlank(cityModel.getDescription(), new LocationCityDescriptionCannotBeEmptyException());
        Validations.validationByLimitCharacters(cityModel.getDescription(), DomainConstants.MAX_DESCRIPTION_SIZE_LOCATION, new LocationDescriptionMaxSizeExceedException());

        Validations.validationByAttributeIsNullOrBlank(departmentModel.getName(), new LocationDepartmentNameCannotBeEmptyException());
        Validations.validationByLimitCharacters(departmentModel.getName(), DomainConstants.MAX_NAME_SIZE_LOCATION, new LocationNameMaxSizeExceedException());
        Validations.validationByAttributeIsNullOrBlank(departmentModel.getDescription(), new LocationDepartmentDescriptionCannotBeEmptyException());
        Validations.validationByLimitCharacters(departmentModel.getDescription(), DomainConstants.MAX_DESCRIPTION_SIZE_LOCATION, new LocationDescriptionMaxSizeExceedException());

        cityModel.setName(cityModel.getName().toUpperCase());
        cityModel.setDescription(cityModel.getDescription().toUpperCase());
        departmentModel.setName(departmentModel.getName().toUpperCase());
        departmentModel.setDescription(departmentModel.getDescription().toUpperCase());

        Optional<DepartmentModel> departmentModelFound = locationPersistencePort.findDepartmentByName(departmentModel.getName());
        if (locationPersistencePort.findCityByName(cityModel.getName()).isPresent() && departmentModelFound.isPresent()){
            throw new LocationAlreadyExistsException();
        }
        if (departmentModelFound.isEmpty()){
            DepartmentModel departmentModelSave = locationPersistencePort.saveDepartment(departmentModel);
            cityModel.setDepartmentModel(departmentModelSave);
            locationPersistencePort.saveCity(cityModel);
        } else {
            cityModel.setDepartmentModel(departmentModelFound.orElse(null));
            locationPersistencePort.saveCity(cityModel);
        }
    }

    @Override
    public Pagination<CityModel> getLocations(String nameLocation, Integer page, Integer size, String orderBy, boolean orderAsc) {
        if (nameLocation.isBlank()) {
            return getAllLocations(page, size, orderBy, orderAsc);
        }
        return getLocationsByNameLocation(nameLocation, page, size, orderBy, orderAsc);
    }

    private Pagination<CityModel> getAllLocations(Integer page, Integer size, String orderBy, boolean orderAsc){
        List<CityModel> cityModelList = new ArrayList<>(locationPersistencePort.getAllCities());

        return paginationOrderBy(cityModelList, page, size, orderBy, orderAsc);
    }

    private Pagination<CityModel> getLocationsByNameLocation(String nameLocation, Integer page, Integer size, String orderBy, boolean orderAsc){

        List<CityModel> locationListFound;
        if (locationPersistencePort.findDepartmentByName(nameLocation.toUpperCase()).isPresent()){
            locationListFound = new ArrayList<>(locationPersistencePort.findAllByDepartmentName(nameLocation.toUpperCase()));
        } else if (locationPersistencePort.findCityByName(nameLocation.toUpperCase()).isPresent()){
            locationListFound = List.of(Objects.requireNonNull(locationPersistencePort.findCityByName(nameLocation.toUpperCase()).orElse(null)));
        } else {
            throw new LocationNotFoundException();
        }

        return paginationOrderBy(locationListFound, page, size, orderBy, orderAsc);
    }

    private Pagination<CityModel> paginationOrderBy(List<CityModel> cityModelList, Integer page, Integer size, String orderBy, boolean orderAsc){

        if (orderBy.equalsIgnoreCase(PaginationConstants.CITY_ORDER_BY_PAGINATION)) {
            return new Pagination<>(cityModelList, page, size, Comparator.comparing(CityModel::getName), orderAsc);
        } else if (orderBy.equalsIgnoreCase(PaginationConstants.DEPARTMENT_ORDER_BY_PAGINATION)){
            return new Pagination<>(cityModelList, page, size, Comparator.comparing(cityModel -> cityModel.getDepartmentModel().getName()), orderAsc);
        } else {
            throw new LocationOrderNotFoundException();
        }
    }
}
