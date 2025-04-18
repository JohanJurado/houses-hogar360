package com.pragma.hogar360_microservice_house.domain.usecases;

import com.pragma.hogar360_microservice_house.domain.exceptions.*;
import com.pragma.hogar360_microservice_house.domain.model.LocationModel;
import com.pragma.hogar360_microservice_house.domain.ports.in.ILocationServicePort;
import com.pragma.hogar360_microservice_house.domain.ports.out.ICityPersistencePort;
import com.pragma.hogar360_microservice_house.domain.ports.out.IDepartmentPersistencePort;
import com.pragma.hogar360_microservice_house.domain.ports.out.ILocationPersistencePort;
import com.pragma.hogar360_microservice_house.domain.util.pagination.Pagination;

import java.util.*;

import static com.pragma.hogar360_microservice_house.domain.util.pagination.PaginationConstants.CITY_ORDER_BY_PAGINATION;
import static com.pragma.hogar360_microservice_house.domain.util.pagination.PaginationConstants.DEPARTMENT_ORDER_BY_PAGINATION;
import static com.pragma.hogar360_microservice_house.domain.util.validations.GlobalValidations.normalizeToUpper;
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

        setDataLocationModel(locationModel);

        if (locationPersistencePort.findByNeighborhoodAndCityId(
                locationModel.getNeighborhood(),
                locationModel.getCityModel().getId()).isPresent()
        ) {
            throw new LocationAlreadyExistsException();
        }

        locationPersistencePort.save(locationModel);
    }

    @Override
    public Pagination<LocationModel> getLocations(String nameLocation, Integer page, Integer size, String orderBy, boolean orderAsc) {
        List<LocationModel> locationListFound = locationPersistencePort.findAllByCityOrDepartment(normalizeToUpper(nameLocation));
        return paginationOrderBy(locationListFound, page, size, orderBy, orderAsc);
    }

    // get locations
    private Pagination<LocationModel> paginationOrderBy(List<LocationModel> locationModelList, Integer page, Integer size, String orderBy, boolean orderAsc){

        Comparator<LocationModel> comparator;
        if (orderBy.equalsIgnoreCase(CITY_ORDER_BY_PAGINATION)) {
            comparator = Comparator.comparing(locationModel -> locationModel.getCityModel().getName());
        } else if (orderBy.equalsIgnoreCase(DEPARTMENT_ORDER_BY_PAGINATION)){
            comparator = Comparator.comparing(locationModel -> locationModel.getCityModel().getDepartmentModel().getName());
        } else {
            throw new LocationOrderNotFoundException();
        }

        return new Pagination<>(locationModelList, page, size, comparator, orderAsc);
    }

    // save location
    private void setDataLocationModel(LocationModel locationModel){
        setDepartmentModelInLocationModel(locationModel);
        setCityModelInLocationModel(locationModel);
    }

    private void setDepartmentModelInLocationModel(LocationModel locationModel){
        locationModel.getCityModel().setDepartmentModel(
                departmentPersistencePort.findByName(locationModel.getCityModel().getDepartmentModel().getName())
                .orElseGet(() -> departmentPersistencePort.save(locationModel.getCityModel().getDepartmentModel()))
        );
    }

    private void setCityModelInLocationModel(LocationModel locationModel){
        locationModel.setCityModel(
                cityPersistencePort.findByNameAndDepartmentId(
                        locationModel.getCityModel().getName(),
                        locationModel.getCityModel().getDepartmentModel().getId()
                )
                .orElseGet(() -> cityPersistencePort.save(locationModel.getCityModel()))
        );
    }
}
