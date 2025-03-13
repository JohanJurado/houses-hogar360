package com.pragma.hogar360_microservice_house.category.domain.model;

import com.pragma.hogar360_microservice_house.category.domain.exceptions.DescriptionMaxSizeException;
import com.pragma.hogar360_microservice_house.category.domain.exceptions.NameMaxSizeException;
import com.pragma.hogar360_microservice_house.category.domain.util.constants.DomainConstants;
import com.pragma.hogar360_microservice_house.commons.configurations.utils.Validations;


public class CategoryModel {
    private Long id;
    private String name;
    private String description;

    public CategoryModel() {
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
        Validations.validationByAtributeIsNullOrBlank(name, DomainConstants.FIELD_NAME_NULL_MESSAGE);
        Validations.validationByLimitCharacters(name, DomainConstants.MAX_NAME_SIZE, new NameMaxSizeException());
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        Validations.validationByAtributeIsNullOrBlank(description, DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
        Validations.validationByLimitCharacters(description, DomainConstants.MAX_DESCRIPTION_SIZE, new DescriptionMaxSizeException());
        this.description = description;
    }
}
