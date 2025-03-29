package com.pragma.hogar360_microservice_house.domain.util.pagination;

import static com.pragma.hogar360_microservice_house.domain.util.constants.GlobalConstants.UTILITY_CLASS_MESSAGE;

public class PaginationConstants {

    private PaginationConstants() {
        throw new IllegalStateException(UTILITY_CLASS_MESSAGE);
    }

    // category
    public static final String NAME_CATEGORY_DEFAULT_PAGINATION = "";

    // location
    public static final String NAME_LOCATION_DEFAULT_PAGINATION = "";
    public static final String ORDER_BY_LOCATION_DEFAULT_PAGINATION = "city";

    // house
    public static final String ORDER_BY_HOUSE_DEFAULT_PAGINATION = "city";
    public static final String CITY_ORDER_BY_PAGINATION = "city";
    public static final String DEPARTMENT_ORDER_BY_PAGINATION = "department";
    public static final String CATEGORY_ORDER_BY_PAGINATION = "category";
    public static final String BEDROOM_ORDER_BY_PAGINATION = "bedroomCount";
    public static final String BATHROOM_ORDER_BY_PAGINATION = "bathroomCount";
    public static final String PRICE_ORDER_BY_PAGINATION = "price";

    // global pagination
    public static final String PAGE_DEFAULT_PAGINATION = "0";
    public static final String SIZE_DEFAULT_PAGINATION = "10";
    public static final String ORDER_ASC_DEFAULT_PAGINATION = "true";

    public static final Integer SIZE_ONE_LIST_PAGINATION = 1;
    public static final Integer PAGE_INVALID_NEGATIVE = 0;
    public static final Integer PAGE_DIFF_INDEX = 1;
}
