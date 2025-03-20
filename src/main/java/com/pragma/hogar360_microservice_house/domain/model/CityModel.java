package com.pragma.hogar360_microservice_house.domain.model;

import com.pragma.hogar360_microservice_house.domain.exceptions.LocationCityDescriptionCannotBeEmptyException;
import com.pragma.hogar360_microservice_house.domain.exceptions.LocationCityNameCannotBeEmptyException;
import com.pragma.hogar360_microservice_house.domain.exceptions.LocationDescriptionMaxSizeExceedException;
import com.pragma.hogar360_microservice_house.domain.exceptions.LocationNameMaxSizeExceedException;
import com.pragma.hogar360_microservice_house.domain.util.constants.DomainConstants;
import com.pragma.hogar360_microservice_house.domain.util.validations.Validations;

public class CityModel {

    private Long id;
    private String name;
    private String description;

    private DepartmentModel departmentModel;

    public CityModel() {
    }

    public CityModel(Long id, String name, String description, DepartmentModel departmentModel) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.departmentModel = departmentModel;
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
        Validations.validationByAttributeIsNullOrBlank(name, new LocationCityNameCannotBeEmptyException());
        Validations.validationByLimitCharacters(name, DomainConstants.MAX_NAME_SIZE_LOCATION, new LocationNameMaxSizeExceedException());
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        Validations.validationByAttributeIsNullOrBlank(description, new LocationCityDescriptionCannotBeEmptyException());
        Validations.validationByLimitCharacters(description, DomainConstants.MAX_DESCRIPTION_SIZE_LOCATION, new LocationDescriptionMaxSizeExceedException());
        this.description = description;
    }

    public DepartmentModel getCity() {
        return departmentModel;
    }

    public void setCity(DepartmentModel departmentModel) {
        this.departmentModel = departmentModel;
    }
}
