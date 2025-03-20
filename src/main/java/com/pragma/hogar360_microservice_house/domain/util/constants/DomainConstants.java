package com.pragma.hogar360_microservice_house.domain.util.constants;

public final class DomainConstants {

    private DomainConstants() {
        throw new IllegalStateException("Utility class");
    }

//    public static final String FIELD_NAME_NULL_MESSAGE = "Field 'name' cannot be null or blank";
//    public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "Field 'description' cannot be null or blank";

    public static final Long MAX_NAME_SIZE_CATEGORY = 50L;
    public static final Long MAX_DESCRIPTION_SIZE_CATEGORY = 90L;

    public static final Long MAX_NAME_SIZE_LOCATION = 50L;
    public static final Long MAX_DESCRIPTION_SIZE_LOCATION = 120L;

}
