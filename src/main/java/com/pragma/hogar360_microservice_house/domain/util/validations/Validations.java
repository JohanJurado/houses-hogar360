package com.pragma.hogar360_microservice_house.domain.util.validations;

import java.util.Objects;

public class Validations {

    private Validations() {
        throw new IllegalStateException("Utility class");
    }

    public static void validationByLimitCharacters(String attribute, Long limit, RuntimeException ex){
        if (attribute.length() > limit){
            throw ex;
        }
    }

    public static void validationByAttributeIsNullOrBlank(String attribute, String messageException){
        attribute = Objects.requireNonNullElse(attribute, "");
        if (attribute.isBlank()){
            throw new NullPointerException(messageException);
        }
    }
}
