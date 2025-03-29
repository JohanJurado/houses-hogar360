package com.pragma.hogar360_microservice_house.domain.util.validations;


import com.pragma.hogar360_microservice_house.domain.exceptions.*;
import com.pragma.hogar360_microservice_house.domain.model.LocationModel;

import static com.pragma.hogar360_microservice_house.domain.util.constants.DomainConstants.*;
import static com.pragma.hogar360_microservice_house.domain.util.constants.GlobalConstants.UTILITY_CLASS_MESSAGE;
import static com.pragma.hogar360_microservice_house.domain.util.validations.GlobalValidations.*;

public class LocationValidation {

    private LocationValidation() {
        throw new IllegalStateException(UTILITY_CLASS_MESSAGE);
    }

    public static void validationByLocationAttributes(LocationModel locationModel) {
        validationEmptyAttributes(locationModel);
        validationLimitCharacters(locationModel);
    }

    public static void toUpperStringLocationAttributes(LocationModel locationModel) {
        locationModel.setNeighborhood(normalizeToUpper(locationModel.getNeighborhood()));

        locationModel.getCityModel().setName(normalizeToUpper(locationModel.getCityModel().getName()));
        locationModel.getCityModel().setDescription(normalizeToUpper(locationModel.getCityModel().getDescription()));
        locationModel.getCityModel().getDepartmentModel().setName(normalizeToUpper(locationModel.getCityModel().getDepartmentModel().getName()));
        locationModel.getCityModel().getDepartmentModel().setDescription(
                normalizeToUpper(locationModel.getCityModel().getDepartmentModel().getDescription())
        );
    }

    private static void validationEmptyAttributes(LocationModel locationModel){
        validationByAttributeIsNullOrBlank(locationModel.getNeighborhood(), new LocationNeighborhoodCannotBeEmptyException());

        validationByAttributeIsNullOrBlank(locationModel.getCityModel().getName(), new CityNameCannotBeEmptyException());
        validationByAttributeIsNullOrBlank(locationModel.getCityModel().getDescription(), new CityDescriptionCannotBeEmptyException());

        validationByAttributeIsNullOrBlank(locationModel.getCityModel().getDepartmentModel().getName(), new DepartmentNameCannotBeEmptyException());
        validationByAttributeIsNullOrBlank(
                locationModel.getCityModel().getDepartmentModel().getDescription(),
                new DepartmentDescriptionCannotBeEmptyException()
        );
    }

    private static void validationLimitCharacters(LocationModel locationModel){
        validationByLimitCharacters(
                locationModel.getCityModel().getName(), MAX_NAME_SIZE_CITY, new LocationNameMaxSizeExceedException()
        );
        validationByLimitCharacters(
                locationModel.getCityModel().getDescription(), MAX_DESCRIPTION_SIZE_CITY,
                new LocationDescriptionMaxSizeExceedException()
        );

        validationByLimitCharacters(
                locationModel.getCityModel().getDepartmentModel().getName(), MAX_NAME_SIZE_DEPARTMENT,
                new LocationNameMaxSizeExceedException()
        );
        validationByLimitCharacters(
                locationModel.getCityModel().getDepartmentModel().getDescription(),
                MAX_DESCRIPTION_SIZE_DEPARTMENT, new LocationDescriptionMaxSizeExceedException()
        );

        validationByLimitCharacters(
                locationModel.getNeighborhood(), MAX_NEIGHBORHOOD_SIZE_LOCATION,
                new LocationNeighborhoodMaxSizeExceedException()
        );
    }
}
