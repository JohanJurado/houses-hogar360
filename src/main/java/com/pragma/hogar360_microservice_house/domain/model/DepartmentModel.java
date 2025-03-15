package com.pragma.hogar360_microservice_house.domain.model;

import com.pragma.hogar360_microservice_house.domain.exceptions.LocationDescriptionMaxSizeExceedException;
import com.pragma.hogar360_microservice_house.domain.exceptions.LocationNameMaxSizeExceedException;
import com.pragma.hogar360_microservice_house.domain.util.constants.DomainConstants;
import com.pragma.hogar360_microservice_house.domain.util.validations.Validations;

public class DepartmentModel {

    private Long id;
    private String name;
    private String description;

    private CityModel city;

    public DepartmentModel() {
        // Empty constructor to validate with setters
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Validations.validationByAttributeIsNullOrBlank(name, DomainConstants.FIELD_NAME_NULL_MESSAGE);
        Validations.validationByLimitCharacters(name, DomainConstants.MAX_NAME_SIZE_LOCATION, new LocationNameMaxSizeExceedException());
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        Validations.validationByAttributeIsNullOrBlank(description, DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
        Validations.validationByLimitCharacters(description, DomainConstants.MAX_DESCRIPTION_SIZE_LOCATION, new LocationDescriptionMaxSizeExceedException());
        this.description = description;
    }

    public CityModel getCity() {
        return city;
    }

    public void setCity(CityModel city) {
        this.city = city;
    }
}
