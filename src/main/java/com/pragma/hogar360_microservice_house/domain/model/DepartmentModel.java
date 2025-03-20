package com.pragma.hogar360_microservice_house.domain.model;

import com.pragma.hogar360_microservice_house.domain.exceptions.LocationDepartmentDescriptionCannotBeEmptyException;
import com.pragma.hogar360_microservice_house.domain.exceptions.LocationDepartmentNameCannotBeEmptyException;
import com.pragma.hogar360_microservice_house.domain.exceptions.LocationDescriptionMaxSizeExceedException;
import com.pragma.hogar360_microservice_house.domain.exceptions.LocationNameMaxSizeExceedException;
import com.pragma.hogar360_microservice_house.domain.util.constants.DomainConstants;
import com.pragma.hogar360_microservice_house.domain.util.validations.Validations;

public class DepartmentModel {

    private Long id;
    private String name;
    private String description;

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
        Validations.validationByAttributeIsNullOrBlank(name, new LocationDepartmentNameCannotBeEmptyException());
        Validations.validationByLimitCharacters(name, DomainConstants.MAX_NAME_SIZE_LOCATION, new LocationNameMaxSizeExceedException());
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        Validations.validationByAttributeIsNullOrBlank(description, new LocationDepartmentDescriptionCannotBeEmptyException());
        Validations.validationByLimitCharacters(description, DomainConstants.MAX_DESCRIPTION_SIZE_LOCATION, new LocationDescriptionMaxSizeExceedException());
        this.description = description;
    }

}
