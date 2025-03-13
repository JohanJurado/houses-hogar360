package com.pragma.hogar360_microservice_house.commons.configurations.utils;

import java.util.Objects;

public class Validations {

    public static void validationByLimitCharacters(String attribute, Long limit, RuntimeException ex){
        if (attribute.length() > limit){
            throw ex;
        }
    }

    public static void validationByAtributeIsNullOrBlank(String attribute, String messageException){
        attribute = Objects.requireNonNullElse(attribute, "");
        if (attribute.isBlank()){
            throw new NullPointerException(messageException);
        }
    }
}
