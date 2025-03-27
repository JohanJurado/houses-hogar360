package com.pragma.hogar360_microservice_house.domain.usecases;

import com.pragma.hogar360_microservice_house.domain.exceptions.*;
import com.pragma.hogar360_microservice_house.domain.model.LocationModel;
import com.pragma.hogar360_microservice_house.domain.ports.in.ILocationServicePort;
import com.pragma.hogar360_microservice_house.domain.ports.out.ICityPersistencePort;
import com.pragma.hogar360_microservice_house.domain.ports.out.IDepartmentPersistencePort;
import com.pragma.hogar360_microservice_house.domain.ports.out.ILocationPersistencePort;
import com.pragma.hogar360_microservice_house.domain.util.pagination.Pagination;
import com.pragma.hogar360_microservice_house.domain.util.pagination.PaginationConstants;

import java.util.*;

import static com.pragma.hogar360_microservice_house.domain.util.validations.LocationValidation.*;

public class LocationUseCase implements ILocationServicePort {

    private final ILocationPersistencePort locationPersistencePort;
    private final ICityPersistencePort cityPersistencePort;
    private final IDepartmentPersistencePort departmentPersistencePort;

    public LocationUseCase(ILocationPersistencePort locationPersistencePort, ICityPersistencePort cityPersistencePort,
                           IDepartmentPersistencePort departmentPersistencePort) {
        this.locationPersistencePort = locationPersistencePort;
        this.cityPersistencePort = cityPersistencePort;
        this.departmentPersistencePort = departmentPersistencePort;
    }

    @Override
    public void save(LocationModel locationModel) {
        validationByLocationAttributes(locationModel);
        toUpperStringLocationAttributes(locationModel);

        setDepartmentModelInLocationModel(locationModel);
        setCityModelInLocationModel(locationModel);

        if (locationPersistencePort.findByNeighborhoodAndCityId(locationModel.getNeighborhood(), locationModel.getCityModel().getId()).isPresent()) {
            throw new LocationAlreadyExistsException();
        }

        locationPersistencePort.save(locationModel);
    }

    private void setDepartmentModelInLocationModel(LocationModel locationModel){
        locationModel.getCityModel().setDepartmentModel(
                departmentPersistencePort.findByName(locationModel.getCityModel().getDepartmentModel().getName())
                .orElse(departmentPersistencePort.save(locationModel.getCityModel().getDepartmentModel()))
        );
    }

    private void setCityModelInLocationModel(LocationModel locationModel){
        locationModel.setCityModel(
                cityPersistencePort.findByNameAndDepartmentId(
                        locationModel.getCityModel().getName(),
                        locationModel.getCityModel().getDepartmentModel().getId()
                )
                .orElse(cityPersistencePort.save(locationModel.getCityModel()))
        );
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
