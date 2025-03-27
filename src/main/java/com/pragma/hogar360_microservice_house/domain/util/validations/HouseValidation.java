package com.pragma.hogar360_microservice_house.domain.util.validations;

import com.pragma.hogar360_microservice_house.domain.exceptions.*;
import com.pragma.hogar360_microservice_house.domain.model.CategoryModel;
import com.pragma.hogar360_microservice_house.domain.model.HouseModel;

import static com.pragma.hogar360_microservice_house.domain.util.constants.DomainConstants.UTILITY_CLASS_MESSAGE;
import static com.pragma.hogar360_microservice_house.domain.util.validations.GlobalValidations.validationByAttributeIsNullOrBlank;
import static com.pragma.hogar360_microservice_house.domain.util.validations.GlobalValidations.validationByAttributeLObjectIsNullOrBlank;

public class HouseValidation {

    private HouseValidation() {
        throw new IllegalStateException(UTILITY_CLASS_MESSAGE);
    }

    public static void validationByHouseAttributes(HouseModel houseModel){
        validationByAttributeIsNullOrBlank(houseModel.getName(), new HouseNameCannotBeEmptyException());
        validationByAttributeIsNullOrBlank(houseModel.getDescription(), new HouseDescriptionCannotBeEmptyException());
        validationByAttributeLObjectIsNullOrBlank(houseModel.getBedroomCount(), new HouseBedroomCountCannotBeEmptyException());
        validationByAttributeLObjectIsNullOrBlank(houseModel.getBathroomCount(), new HouseBathroomCountCannotBeEmptyException());
        validationByAttributeLObjectIsNullOrBlank(houseModel.getPrice(), new HousePriceCannotBeEmptyException());
        validationByAttributeLObjectIsNullOrBlank(houseModel.getActivePublicationDate(), new HouseActivePublicationDateCannotBeEmptyException());

        validationByAttributeIsNullOrBlank(houseModel.getCityModel().getName(), new HouseLocationCannotBeEmptyException());
        validationByAttributeIsNullOrBlank(houseModel.getCityModel().getDepartmentModel().getName(), new HouseLocationCannotBeEmptyException());
        validationByAttributeIsNullOrBlank(houseModel.getCategoryModel().getName(), new HouseCategoryCannotBeEmptyException());
    }

    public static void toUpperStringHouseAttributes(HouseModel houseModel){
        houseModel.setName(houseModel.getName().toUpperCase());
        houseModel.setDescription(houseModel.getDescription().toUpperCase());
    }
}
