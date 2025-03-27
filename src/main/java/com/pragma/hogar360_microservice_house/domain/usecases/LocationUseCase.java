package com.pragma.hogar360_microservice_house.domain.usecases;

import com.pragma.hogar360_microservice_house.domain.exceptions.*;
import com.pragma.hogar360_microservice_house.domain.model.CityModel;
import com.pragma.hogar360_microservice_house.domain.model.DepartmentModel;
import com.pragma.hogar360_microservice_house.domain.model.LocationModel;
import com.pragma.hogar360_microservice_house.domain.ports.in.ILocationServicePort;
import com.pragma.hogar360_microservice_house.domain.ports.out.ICityPersistencePort;
import com.pragma.hogar360_microservice_house.domain.ports.out.IDepartmentPersistencePort;
import com.pragma.hogar360_microservice_house.domain.ports.out.ILocationPersistencePort;
import com.pragma.hogar360_microservice_house.domain.util.constants.DomainConstants;
import com.pragma.hogar360_microservice_house.domain.util.pagination.Pagination;
import com.pragma.hogar360_microservice_house.domain.util.pagination.PaginationConstants;
import com.pragma.hogar360_microservice_house.domain.util.validations.Validations;

import java.util.*;

public class LocationUseCase implements ILocationServicePort {

    private final ILocationPersistencePort locationPersistencePort;
    private final ICityPersistencePort cityPersistencePort;
    private final IDepartmentPersistencePort departmentPersistencePort;

    public LocationUseCase(ILocationPersistencePort locationPersistencePort, ICityPersistencePort cityPersistencePort, IDepartmentPersistencePort departmentPersistencePort) {
        this.locationPersistencePort = locationPersistencePort;
        this.cityPersistencePort = cityPersistencePort;
        this.departmentPersistencePort = departmentPersistencePort;
    }

    @Override
    public void save(LocationModel locationModel) {
        Validations.validationByAttributeIsNullOrBlank(locationModel.getCityModel().getName(), new CityNameCannotBeEmptyException());
        Validations.validationByAttributeIsNullOrBlank(locationModel.getCityModel().getDescription(), new CityDescriptionCannotBeEmptyException());
        Validations.validationByLimitCharacters(locationModel.getCityModel().getName(), DomainConstants.MAX_NAME_SIZE_LOCATION, new LocationNameMaxSizeExceedException());
        Validations.validationByLimitCharacters(locationModel.getCityModel().getDescription(), DomainConstants.MAX_DESCRIPTION_SIZE_LOCATION, new LocationDescriptionMaxSizeExceedException());

        Validations.validationByAttributeIsNullOrBlank(locationModel.getCityModel().getDepartmentModel().getName(), new DepartmentNameCannotBeEmptyException());
        Validations.validationByAttributeIsNullOrBlank(locationModel.getCityModel().getDepartmentModel().getDescription(), new DepartmentDescriptionCannotBeEmptyException());
        Validations.validationByLimitCharacters(locationModel.getCityModel().getDepartmentModel().getName(), DomainConstants.MAX_NAME_SIZE_LOCATION, new LocationNameMaxSizeExceedException());
        Validations.validationByLimitCharacters(locationModel.getCityModel().getDepartmentModel().getDescription(), DomainConstants.MAX_DESCRIPTION_SIZE_LOCATION, new LocationDescriptionMaxSizeExceedException());

        Validations.validationByAttributeIsNullOrBlank(locationModel.getNeighborhood(), new LocationNeighborhoodCannotBeEmptyException());
        Validations.validationByLimitCharacters(locationModel.getNeighborhood(), DomainConstants.MAX_NEIGHBORHOOD_SIZE_LOCATION, new LocationNeighborhoodMaxSizeExceedException());

        locationModel.getCityModel().setName(locationModel.getCityModel().getName().toUpperCase());
        locationModel.getCityModel().setDescription(locationModel.getCityModel().getDescription().toUpperCase());
        locationModel.getCityModel().getDepartmentModel().setName(locationModel.getCityModel().getDepartmentModel().getName().toUpperCase());
        locationModel.getCityModel().getDepartmentModel().setDescription(locationModel.getCityModel().getDepartmentModel().getDescription().toUpperCase());

        DepartmentModel departmentModel = departmentPersistencePort.findByName(locationModel.getCityModel().getDepartmentModel().getName())
                .orElse(departmentPersistencePort.save(locationModel.getCityModel().getDepartmentModel()));
        locationModel.getCityModel().setDepartmentModel(departmentModel);

        CityModel cityModel = cityPersistencePort.findByNameAndDepartmentId(locationModel.getCityModel().getName(), departmentModel.getId())
                .orElse(cityPersistencePort.save(locationModel.getCityModel()));
        locationModel.setCityModel(cityModel);

        if (locationPersistencePort.findByNeighborhoodAndCityId(locationModel.getNeighborhood(), cityModel.getId()).isPresent()) {
            throw new LocationAlreadyExistsException();
        }

        locationPersistencePort.save(locationModel);
    }

    @Override
    public Pagination<LocationModel> getLocations(String nameLocation, Integer page, Integer size, String orderBy, boolean orderAsc) {
        if (nameLocation.isBlank()) {
            return getAllLocations(page, size, orderBy, orderAsc);
        }
        return getLocationsByNameLocation(nameLocation, page, size, orderBy, orderAsc);
    }

    private Pagination<LocationModel> getAllLocations(Integer page, Integer size, String orderBy, boolean orderAsc){
        List<LocationModel> locationModelList = locationPersistencePort.getAllLocations();

        return paginationOrderBy(locationModelList, page, size, orderBy, orderAsc);
    }

    private Pagination<LocationModel> getLocationsByNameLocation(String nameLocation, Integer page, Integer size, String orderBy, boolean orderAsc){

        List<LocationModel> locationListFound;
        if (departmentPersistencePort.findByName(nameLocation.toUpperCase()).isPresent()){
            locationListFound = locationPersistencePort.findAllByDepartmentName(nameLocation.toUpperCase());
        } else {
            locationListFound = locationPersistencePort.findAllByCityName(nameLocation.toUpperCase());
        }

        return paginationOrderBy(locationListFound, page, size, orderBy, orderAsc);
    }

    private Pagination<LocationModel> paginationOrderBy(List<LocationModel> locationModelList, Integer page, Integer size, String orderBy, boolean orderAsc){

        Comparator<LocationModel> comparator;
        if (orderBy.equalsIgnoreCase(PaginationConstants.CITY_ORDER_BY_PAGINATION)) {
            comparator = Comparator.comparing(locationModel -> locationModel.getCityModel().getName());
        } else if (orderBy.equalsIgnoreCase(PaginationConstants.DEPARTMENT_ORDER_BY_PAGINATION)){
            comparator = Comparator.comparing(locationModel -> locationModel.getCityModel().getDepartmentModel().getName());
        } else {
            throw new LocationOrderNotFoundException();
        }

        return new Pagination<>(locationModelList, page, size, comparator, orderAsc);
    }
}
