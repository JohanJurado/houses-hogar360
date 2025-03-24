package com.pragma.hogar360_microservice_house.application.utils;

import com.pragma.hogar360_microservice_house.domain.util.constants.DomainConstants;

public class ApplicationConstants {
    private ApplicationConstants() {
        throw new IllegalStateException(DomainConstants.UTILITY_CLASS_MESSAGE);
    }

    public static final String SAVE_CATEGORY_RESPONSE_MESSAGE = "Category saved successfully.";
    public static final String SAVE_LOCATION_RESPONSE_MESSAGE = "Location saved successfully.";
    public static final String SAVE_HOUSE_RESPONSE_MESSAGE = "House saved successfully.";

}
