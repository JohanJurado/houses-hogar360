package com.pragma.hogar360_microservice_house.domain.usecases;

import com.pragma.hogar360_microservice_house.domain.exceptions.*;
import com.pragma.hogar360_microservice_house.domain.model.CityModel;
import com.pragma.hogar360_microservice_house.domain.model.DepartmentModel;
import com.pragma.hogar360_microservice_house.domain.ports.in.ILocationServicePort;
import com.pragma.hogar360_microservice_house.domain.ports.out.ILocationPersistencePort;
import com.pragma.hogar360_microservice_house.domain.util.pagination.Pagination;

import java.util.*;

public class LocationUseCase implements ILocationServicePort {

    private final ILocationPersistencePort locationPersistencePort;

    public LocationUseCase(ILocationPersistencePort locationPersistencePort) {
        this.locationPersistencePort = locationPersistencePort;
    }

    @Override
    public void save(CityModel cityModel, DepartmentModel departmentModel) {
        if (locationPersistencePort.findDepartmentByName(departmentModel.getName()).isPresent()){
            throw new LocationAlreadyExistsException();
        }
        Optional<CityModel> cityModelFound = locationPersistencePort.findCityByName(cityModel.getName());
        if (cityModelFound.isEmpty()){
            CityModel cityModelSave = locationPersistencePort.saveCity(cityModel);
            departmentModel.setCity(cityModelSave);
            locationPersistencePort.saveDepartment(departmentModel);
        } else {
            departmentModel.setCity(cityModelFound.orElse(null));
            locationPersistencePort.saveDepartment(departmentModel);
        }
    }

    @Override
    public Pagination<DepartmentModel> getLocations(String nameLocation, Integer page, Integer size, String orderBy, boolean orderAsc) {
        if (nameLocation.isBlank()) {
            return getAllLocations(page, size, orderBy, orderAsc);
        }
        return getLocationsByNameLocation(nameLocation, page, size, orderBy, orderAsc);
    }

    private Pagination<DepartmentModel> getAllLocations(Integer page, Integer size, String orderBy, boolean orderAsc){
        List<DepartmentModel> departmentModels = new ArrayList<>(locationPersistencePort.getAllDepartments());

        List<DepartmentModel> pageContent = paginationContent(orderList(departmentModels, orderBy, orderAsc), page, size);

        return new Pagination<>(pageContent, page, size, departmentModels.size());
    }

    private Pagination<DepartmentModel> getLocationsByNameLocation(String nameLocation, Integer page, Integer size, String orderBy, boolean orderAsc){

        List<DepartmentModel> locationListFound;
        if (locationPersistencePort.findCityByName(nameLocation.toUpperCase()).isPresent()){
            locationListFound = new ArrayList<>(locationPersistencePort.findAllByCityName(nameLocation));
        } else if (locationPersistencePort.findDepartmentByName(nameLocation.toUpperCase()).isPresent()){
            locationListFound = List.of(Objects.requireNonNull(locationPersistencePort.findDepartmentByName(nameLocation.toUpperCase()).orElse(null)));
        } else {
            throw new LocationNotFoundException();
        }

        List<DepartmentModel> orderLocationListFound = orderList(locationListFound, orderBy, orderAsc);

        List<DepartmentModel> pageContent = paginationContent(orderLocationListFound, page, size);

        return new Pagination<>(pageContent, page, size, locationListFound.size());
    }

    private List<DepartmentModel> paginationContent(List<DepartmentModel> departmentModelList, Integer page, Integer size){

        int totalElements = departmentModelList.size();
        int fromIndex = (page-1)*size;
        int toIndex = Math.min(fromIndex + size, totalElements);

        if (fromIndex >= totalElements || fromIndex < 0) {
            throw new PageNotFoundException();
        }

        return departmentModelList.subList(fromIndex, toIndex);
    }

    private List<DepartmentModel> orderList(List<DepartmentModel> departmentModelList, String orderBy, boolean orderAsc){

        if (orderBy.equalsIgnoreCase("city")){
            List<DepartmentModel> orderDepartmentModelList;

            if (orderAsc) {
                orderDepartmentModelList = departmentModelList.stream()
                        .sorted(Comparator.comparing(department -> department.getCity().getName())).toList();
            } else {
                orderDepartmentModelList = departmentModelList.stream()
                        .sorted(Comparator.comparing((DepartmentModel department) -> department.getCity().getName()).reversed())
                        .toList();
            }
            return orderDepartmentModelList;
        } else if (orderBy.equalsIgnoreCase("department")){
            if (departmentModelList.size() == 1){
                return departmentModelList;
            }

            if (orderAsc) {
                departmentModelList.sort(Comparator.comparing(DepartmentModel::getName));
            } else {
                departmentModelList.sort(Comparator.comparing(DepartmentModel::getName).reversed());
            }
            return departmentModelList;
        } else {
            throw new LocationOrderNotFoundException();
        }
    }
}
