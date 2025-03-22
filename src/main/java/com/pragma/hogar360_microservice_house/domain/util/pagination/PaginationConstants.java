package com.pragma.hogar360_microservice_house.domain.util.pagination;

import com.pragma.hogar360_microservice_house.domain.util.constants.DomainConstants;

public class PaginationConstants {

    private PaginationConstants() {
        throw new IllegalStateException(DomainConstants.UTILITY_CLASS_MESSAGE);
    }

    public static final String NAME_CATEGORY_DEFAULT_PAGINATION = "";
    public static final String NAME_LOCATION_DEFAULT_PAGINATION = "";

    public static final String DEPARTMENT_ORDER_BY_PAGINATION = "department";
    public static final String CITY_ORDER_BY_PAGINATION = "city";
    public static final String ORDER_BY_DEFAULT_PAGINATION = "city";

    public static final String PAGE_DEFAULT_PAGINATION = "0";
    public static final String SIZE_DEFAULT_PAGINATION = "10";
    public static final String ORDER_ASC_DEFAULT_PAGINATION = "true";

    public static final int PAGE_INVALID_NEGATIVE = 0;

}
