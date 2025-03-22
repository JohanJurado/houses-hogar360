package com.pragma.hogar360_microservice_house.domain.util.validations;

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
}
