package com.pragma.hogar360_microservice_house.domain.util.validations;

import java.text.Normalizer;
import java.util.Objects;

import static com.pragma.hogar360_microservice_house.domain.util.constants.DomainConstants.*;
import static com.pragma.hogar360_microservice_house.domain.util.constants.GlobalConstants.UTILITY_CLASS_MESSAGE;

public class GlobalValidations {

    private GlobalValidations() {
        throw new IllegalStateException(UTILITY_CLASS_MESSAGE);
    }

    public static void validationByLimitCharacters(String attribute, Long limit, RuntimeException ex){
        if (attribute.length() > limit){
            throw ex;
        }
    }

    public static void validationByAttributeIsNullOrBlank(String attribute, RuntimeException ex){
        attribute = Objects.requireNonNullElse(attribute, VALIDATIONS_STR_FROM_NULL_TO_BLANK);
        if (attribute.isBlank()){
            throw ex;
        }
    }

    public static void validationByAttributeLObjectIsNullOrBlank(Object attribute, RuntimeException ex){
        if (attribute == null){
            throw ex;
        }
    }

    public static String normalizeToUpper(String attribute) {
        String normalized = Normalizer.normalize(attribute, Normalizer.Form.NFD);
        normalized = normalized.replaceAll(VALIDATIONS_STR_REGEX, VALIDATIONS_STR_REGEX_TO_BLANK);
        return normalized.toUpperCase();
    }
}
