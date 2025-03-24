package com.pragma.hogar360_microservice_house.domain.util.validations;

import com.pragma.hogar360_microservice_house.domain.exceptions.*;
import com.pragma.hogar360_microservice_house.domain.model.HouseModel;
import com.pragma.hogar360_microservice_house.domain.util.constants.DomainConstants;

import java.util.Objects;

public class Validations {

    private Validations() {
        throw new IllegalStateException(DomainConstants.UTILITY_CLASS_MESSAGE);
    }

    public static void validationByLimitCharacters(String attribute, Long limit, RuntimeException ex){
        if (attribute.length() > limit){
            throw ex;
        }
    }

    public static void validationByAttributeIsNullOrBlank(String attribute, RuntimeException ex){
        attribute = Objects.requireNonNullElse(attribute, DomainConstants.VALIDATIONS_STR_FROM_NULL_TO_BLANK);
        if (attribute.isBlank()){
            throw ex;
        }
    }

    public static void validationByAttributeLObjectIsNullOrBlank(Object attribute, RuntimeException ex){
        if (attribute == null){
            throw ex;
        }
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
}
