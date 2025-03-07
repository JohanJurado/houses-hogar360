package com.pragma.hogar360_microservice_house.category.domain.util.constants;

public final class DomainConstants {

    private DomainConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String FIELD_NAME_NULL_MESSAGE = "Field 'name' can not be null";
    public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "Field 'description' can not be null";
    public static final int MAX_NAME_SIZE = 50;
    public static final int MAX_DESCRIPTION_SIZE = 90;
}
