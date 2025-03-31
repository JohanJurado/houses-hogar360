package com.pragma.hogar360_microservice_house.domain.util.validations;

import com.pragma.hogar360_microservice_house.domain.exceptions.*;
import com.pragma.hogar360_microservice_house.domain.model.HouseModel;

import java.time.LocalDate;

import static com.pragma.hogar360_microservice_house.domain.util.constants.DomainConstants.ONE_MONTH;
import static com.pragma.hogar360_microservice_house.domain.util.constants.GlobalConstants.UTILITY_CLASS_MESSAGE;
import static com.pragma.hogar360_microservice_house.domain.util.validations.GlobalValidations.*;

public class HouseValidation {

    private HouseValidation() {
        throw new IllegalStateException(UTILITY_CLASS_MESSAGE);
    }

    public static void validationByHouseAttributes(HouseModel houseModel){
        validationEmptyAttributes(houseModel);
        validationByActivePublicationDate(houseModel.getActivePublicationDate());
    }

    public static void toUpperStringHouseAttributes(HouseModel houseModel){
        houseModel.setName(normalizeToUpper(houseModel.getName()));
        houseModel.setDescription(normalizeToUpper(houseModel.getDescription()));
        houseModel.getCategoryModel().setName(normalizeToUpper(houseModel.getCategoryModel().getName()));
        houseModel.getLocationModel().setNeighborhood(normalizeToUpper(houseModel.getLocationModel().getNeighborhood()));
        houseModel.getLocationModel().getCityModel().setName(normalizeToUpper(houseModel.getLocationModel().getCityModel().getName()));
        houseModel.getLocationModel().getCityModel().getDepartmentModel().setName(
                normalizeToUpper(houseModel.getLocationModel().getCityModel().getDepartmentModel().getName())
        );
    }

    private static void validationByActivePublicationDate(LocalDate activePublicationDate){
        if (activePublicationDate.isAfter(LocalDate.now().plusMonths(ONE_MONTH))){
            throw new HouseLimitActivePublicationDateExceedException();
        }
    }

    private static void validationEmptyAttributes(HouseModel houseModel) {
        validationByAttributeIsNullOrBlank(houseModel.getName(), new HouseNameCannotBeEmptyException());
        validationByAttributeIsNullOrBlank(houseModel.getDescription(), new HouseDescriptionCannotBeEmptyException());
        validationByAttributeLObjectIsNullOrBlank(houseModel.getBedroomCount(), new HouseBedroomCountCannotBeEmptyException());
        validationByAttributeLObjectIsNullOrBlank(houseModel.getBathroomCount(), new HouseBathroomCountCannotBeEmptyException());
        validationByAttributeLObjectIsNullOrBlank(houseModel.getPrice(), new HousePriceCannotBeEmptyException());
        validationByAttributeLObjectIsNullOrBlank(houseModel.getActivePublicationDate(), new HouseActivePublicationDateCannotBeEmptyException());

        validationByAttributeIsNullOrBlank(houseModel.getLocationModel().getNeighborhood(), new LocationNeighborhoodCannotBeEmptyException());
        validationByAttributeIsNullOrBlank(houseModel.getLocationModel().getCityModel().getName(), new HouseLocationCannotBeEmptyException());
        validationByAttributeIsNullOrBlank(houseModel.getLocationModel().getCityModel().getDepartmentModel().getName(), new HouseLocationCannotBeEmptyException());
        validationByAttributeIsNullOrBlank(houseModel.getCategoryModel().getName(), new HouseCategoryCannotBeEmptyException());
    }
}
