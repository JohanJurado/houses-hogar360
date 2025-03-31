package com.pragma.hogar360_microservice_house.domain.usecases;

import com.pragma.hogar360_microservice_house.domain.exceptions.*;
import com.pragma.hogar360_microservice_house.domain.model.*;
import com.pragma.hogar360_microservice_house.domain.model.filters.HouseFilterModel;
import com.pragma.hogar360_microservice_house.domain.ports.in.IHouseServicePort;
import com.pragma.hogar360_microservice_house.domain.ports.out.ICategoryPersistencePort;
import com.pragma.hogar360_microservice_house.domain.ports.out.IHousePersistencePort;
import com.pragma.hogar360_microservice_house.domain.ports.out.ILocationPersistencePort;
import com.pragma.hogar360_microservice_house.domain.util.pagination.Pagination;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import static com.pragma.hogar360_microservice_house.domain.util.constants.StateHousesConstants.PUBLISHED_STATE_HOUSE;
import static com.pragma.hogar360_microservice_house.domain.util.pagination.PaginationConstants.*;
import static com.pragma.hogar360_microservice_house.domain.util.validations.HouseValidation.toUpperStringHouseAttributes;
import static com.pragma.hogar360_microservice_house.domain.util.validations.HouseValidation.validationByHouseAttributes;

public class HouseUseCase implements IHouseServicePort {

    private final IHousePersistencePort housePersistencePort;
    private final ICategoryPersistencePort categoryPersistencePort;
    private final ILocationPersistencePort locationPersistencePort;

    public HouseUseCase(IHousePersistencePort housePersistencePort, ICategoryPersistencePort categoryPersistencePort, ILocationPersistencePort locationPersistencePort) {
        this.housePersistencePort = housePersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
        this.locationPersistencePort = locationPersistencePort;
    }

    @Override
    public void publish(HouseModel houseModel) {
        validationByHouseAttributes(houseModel);
        toUpperStringHouseAttributes(houseModel);

        setDataHouse(houseModel);

        housePersistencePort.save(houseModel);
    }

    @Override
    public Pagination<HouseModel> getHouses(HouseFilterModel filterModel, Integer page, Integer size,
                                            String orderBy, boolean orderAsc) {
        List<HouseModel> houseModelFilterList = housePersistencePort.findHousesByFilters(filterModel, PUBLISHED_STATE_HOUSE);

        return new Pagination<>(houseModelFilterList, page, size, defineHouseAttributeToSort(orderBy), orderAsc);
    }

    // get houses
    private Comparator<HouseModel> defineHouseAttributeToSort(String orderBy){

        Comparator<HouseModel> comparator;
        if (orderBy.equalsIgnoreCase(PRICE_ORDER_BY_PAGINATION)){
            comparator = Comparator.comparing(HouseModel::getPrice);
        } else if (orderBy.equalsIgnoreCase(DEPARTMENT_ORDER_BY_PAGINATION)){
            comparator = Comparator.comparing(houseModel -> houseModel.getLocationModel().getCityModel().getDepartmentModel().getName());
        } else if (orderBy.equalsIgnoreCase(CITY_ORDER_BY_PAGINATION)) {
            comparator = Comparator.comparing(houseModel -> houseModel.getLocationModel().getCityModel().getName());
        } else if (orderBy.equalsIgnoreCase(BATHROOM_ORDER_BY_PAGINATION)) {
            comparator = Comparator.comparing(HouseModel::getBathroomCount);
        }  else if (orderBy.equalsIgnoreCase(BEDROOM_ORDER_BY_PAGINATION)) {
            comparator = Comparator.comparing(HouseModel::getBedroomCount);
        } else if (orderBy.equalsIgnoreCase(CATEGORY_ORDER_BY_PAGINATION)) {
            comparator = Comparator.comparing(houseModel -> houseModel.getCategoryModel().getName());
        } else {
            throw new HouseOrderNotFoundException();
        }

        return comparator;
    }

    // save house
    private void setDataHouse(HouseModel houseModel){
        setLocationModelInHouseModel(houseModel);
        setCategoryModelInHouseModel(houseModel);

        houseModel.setPublicationDate(LocalDate.now());
        houseModel.calculateInitialStatus();
    }

    private void setLocationModelInHouseModel(HouseModel houseModel){
        houseModel.setLocationModel(
                locationPersistencePort.findByNeighborhoodAndCityNameAndDepartmentName(
                        houseModel.getLocationModel().getNeighborhood(),
                        houseModel.getLocationModel().getCityModel().getName(),
                        houseModel.getLocationModel().getCityModel().getDepartmentModel().getName()
                ).orElseThrow(LocationNotFoundException::new)
        );
    }

    private void setCategoryModelInHouseModel(HouseModel houseModel){
        houseModel.setCategoryModel(
                categoryPersistencePort.findByName(houseModel.getCategoryModel().getName())
                .orElseThrow(CategoryNotFoundException::new)
        );
    }
}
