package com.pragma.hogar360_microservice_house.domain.util.validations;

import com.pragma.hogar360_microservice_house.domain.exceptions.*;
import com.pragma.hogar360_microservice_house.domain.model.LocationModel;

import static com.pragma.hogar360_microservice_house.domain.util.constants.DomainConstants.*;
import static com.pragma.hogar360_microservice_house.domain.util.validations.GlobalValidations.validationByAttributeIsNullOrBlank;
import static com.pragma.hogar360_microservice_house.domain.util.validations.GlobalValidations.validationByLimitCharacters;

public class LocationValidation {

    private LocationValidation() {
        throw new IllegalStateException(UTILITY_CLASS_MESSAGE);
    }

    public static void validationByLocationAttributes(LocationModel locationModel) {
        validationEmptyAttributes(locationModel);
        validationLimitCharacters(locationModel);
    }

    public static void toUpperStringLocationAttributes(LocationModel locationModel) {
        locationModel.getCityModel().setName(locationModel.getCityModel().getName().toUpperCase());
        locationModel.getCityModel().setDescription(locationModel.getCityModel().getDescription().toUpperCase());
        locationModel.getCityModel().getDepartmentModel().setName(locationModel.getCityModel().getDepartmentModel().getName().toUpperCase());
        locationModel.getCityModel().getDepartmentModel().setDescription(
                locationModel.getCityModel().getDepartmentModel().getDescription().toUpperCase()
        );
    }

    private static void validationEmptyAttributes(LocationModel locationModel){
        validationByAttributeIsNullOrBlank(locationModel.getCityModel().getName(), new CityNameCannotBeEmptyException());
        validationByAttributeIsNullOrBlank(locationModel.getCityModel().getDescription(), new CityDescriptionCannotBeEmptyException());

        validationByAttributeIsNullOrBlank(locationModel.getCityModel().getDepartmentModel().getName(), new DepartmentNameCannotBeEmptyException());
        validationByAttributeIsNullOrBlank(locationModel.getCityModel().getDepartmentModel().getDescription(), new DepartmentDescriptionCannotBeEmptyException());

        validationByAttributeIsNullOrBlank(locationModel.getNeighborhood(), new LocationNeighborhoodCannotBeEmptyException());
    }

    private static void validationLimitCharacters(LocationModel locationModel){
        validationByLimitCharacters(
                locationModel.getCityModel().getName(), MAX_NAME_SIZE_LOCATION, new LocationNameMaxSizeExceedException()
        );
        validationByLimitCharacters(
                locationModel.getCityModel().getDescription(), MAX_DESCRIPTION_SIZE_LOCATION,
                new LocationDescriptionMaxSizeExceedException()
        );

        validationByLimitCharacters(
                locationModel.getCityModel().getDepartmentModel().getName(), MAX_NAME_SIZE_LOCATION,
                new LocationNameMaxSizeExceedException()
        );
        validationByLimitCharacters(
                locationModel.getCityModel().getDepartmentModel().getDescription(),
                MAX_DESCRIPTION_SIZE_LOCATION, new LocationDescriptionMaxSizeExceedException()
        );

        validationByLimitCharacters(
                locationModel.getNeighborhood(), MAX_NEIGHBORHOOD_SIZE_LOCATION,
                new LocationNeighborhoodMaxSizeExceedException()
        );
    }
}
