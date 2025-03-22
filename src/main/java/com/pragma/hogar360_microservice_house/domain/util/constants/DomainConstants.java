package com.pragma.hogar360_microservice_house.domain.util.constants;

public final class DomainConstants {

    private DomainConstants() {
        throw new IllegalStateException(UTILITY_CLASS_MESSAGE);
    }

    public static final Long MAX_NAME_SIZE_CATEGORY = 50L;
    public static final Long MAX_DESCRIPTION_SIZE_CATEGORY = 90L;

    public static final Long MAX_NAME_SIZE_LOCATION = 50L;
    public static final Long MAX_DESCRIPTION_SIZE_LOCATION = 120L;

    public static final String UTILITY_CLASS_MESSAGE = "Utility class";

    public static final String VALIDATIONS_STR_FROM_NULL_TO_BLANK = "";
}
