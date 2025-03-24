package com.pragma.hogar360_microservice_house.domain.usecases;

import com.pragma.hogar360_microservice_house.domain.exceptions.CategoryNotFoundException;
import com.pragma.hogar360_microservice_house.domain.exceptions.LocationCityNotFoundException;
import com.pragma.hogar360_microservice_house.domain.exceptions.LocationDepartmentNotFoundException;
import com.pragma.hogar360_microservice_house.domain.exceptions.LocationNotFoundException;
import com.pragma.hogar360_microservice_house.domain.model.CategoryModel;
import com.pragma.hogar360_microservice_house.domain.model.CityModel;
import com.pragma.hogar360_microservice_house.domain.model.DepartmentModel;
import com.pragma.hogar360_microservice_house.domain.model.HouseModel;
import com.pragma.hogar360_microservice_house.domain.ports.in.IHouseServicePort;
import com.pragma.hogar360_microservice_house.domain.ports.out.ICategoryPersistencePort;
import com.pragma.hogar360_microservice_house.domain.ports.out.IHousePersistencePort;
import com.pragma.hogar360_microservice_house.domain.ports.out.ILocationPersistencePort;
import com.pragma.hogar360_microservice_house.domain.util.constants.StateHousesConstants;
import com.pragma.hogar360_microservice_house.domain.util.validations.Validations;

import java.time.LocalDate;
import java.util.List;

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
        Validations.validationByHouseAttributes(houseModel);

        houseModel.setName(houseModel.getName().toUpperCase());
        houseModel.setDescription(houseModel.getDescription().toUpperCase());

        List<CityModel> cityModelList = locationPersistencePort.findCityByName(houseModel.getCityModel().getName().toUpperCase())
                .filter(list -> !list.isEmpty())
                .orElseThrow(LocationCityNotFoundException::new);
        DepartmentModel departmentModel = locationPersistencePort.findDepartmentByName(houseModel.getCityModel().getDepartmentModel().getName().toUpperCase()).orElseThrow(LocationDepartmentNotFoundException::new);

        if (cityModelList.getFirst().getDepartmentModel().getName().equalsIgnoreCase(departmentModel.getName())){
            houseModel.setCityModel(cityModelList.getFirst());
        } else {
            throw new LocationNotFoundException();
        }

        CategoryModel categoryModel = categoryPersistencePort.findByName(houseModel.getCategoryModel().getName().toUpperCase()).orElseThrow(CategoryNotFoundException::new);
        houseModel.setCategoryModel(categoryModel);

        houseModel.setPublicationDate(LocalDate.now());
        if (houseModel.getPublicationDate().isAfter(houseModel.getActivePublicationDate()) || houseModel.getPublicationDate().isEqual(houseModel.getActivePublicationDate())){
            houseModel.setPublicationStatus(StateHousesConstants.PUBLISHED_STATE_HOUSE);
        } else {
            houseModel.setPublicationStatus(StateHousesConstants.PAUSED_STATE_HOUSE);
        }

        housePersistencePort.save(houseModel);
    }
}
