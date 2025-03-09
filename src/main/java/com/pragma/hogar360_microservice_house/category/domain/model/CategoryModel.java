package com.pragma.hogar360_microservice_house.category.domain.model;

import com.pragma.hogar360_microservice_house.category.domain.exceptions.DescriptionMaxSizeException;
import com.pragma.hogar360_microservice_house.category.domain.exceptions.NameMaxSizeException;
import com.pragma.hogar360_microservice_house.category.domain.util.constants.DomainConstants;

import java.util.Objects;

public class CategoryModel {
    private Long id;
    private String name;
    private String description;

    public CategoryModel(Long id, String name, String description) {
        if (name.length() > DomainConstants.MAX_NAME_SIZE) throw new NameMaxSizeException();
        if (description.length() > DomainConstants.MAX_DESCRIPTION_SIZE) throw new DescriptionMaxSizeException();
        this.id = id;
        this.name = Objects.requireNonNull(name, DomainConstants.FIELD_NAME_NULL_MESSAGE);
        this.description = Objects.requireNonNull(description, DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
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
        if (name.length() > DomainConstants.MAX_NAME_SIZE) throw new NameMaxSizeException();
        this.name = Objects.requireNonNull(name, DomainConstants.FIELD_NAME_NULL_MESSAGE);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description.length() > DomainConstants.MAX_DESCRIPTION_SIZE) throw new DescriptionMaxSizeException();
        this.description = Objects.requireNonNull(description, DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
    }
}
