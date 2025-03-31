package com.pragma.hogar360_microservice_house.utils.constants;

import java.time.LocalDate;

public class HouseTestConstants {
    public static final Long VALID_HOUSE_ID = 1L;
    public static final Long VALID_CATEGORY_ID = 1L;
    public static final Long VALID_LOCATION_ID = 1L;

    public static final String VALID_HOUSE_NAME = "Casa campestre";
    public static final String VALID_HOUSE_DESCRIPTION = "Hermosa casa con vista al valle";
    public static final Long VALID_BEDROOM_COUNT = 3L;
    public static final Long VALID_BATHROOM_COUNT = 2L;
    public static final Double VALID_PRICE = 1500000.0;
    public static final String VALID_NEIGHBORHOOD = "El Poblado";
    public static final String VALID_CITY = "Medellín";
    public static final String VALID_DEPARTMENT = "Antioquia";
    public static final String VALID_CATEGORY = "Campestre";
    public static final LocalDate VALID_ACTIVE_DATE = LocalDate.now().plusDays(1);
    public static final LocalDate INVALID_FUTURE_ACTIVE_DATE = LocalDate.now().plusMonths(2);

    public static final String STATE_PUBLICATION_PUBLISHED = "PUBLISHED";
    public static final String STATE_PUBLICATION_PAUSED = "PAUSED";

    public static final String ORDER_BY_OTHER_PAGINATION = "Other";
    public static final boolean ORDER_ASC_PAGINATION = true;

    public static final Double MIN_PRICE_FILTER = 1000000.0;
    public static final Double MAX_PRICE_FILTER = 2000000.0;
    public static final Long BEDROOM_FILTER = 2L;
    public static final Long BATHROOM_FILTER = 1L;

    public static final String EMPTY_STRING = "";
    public static final String NULL_STRING = null;

    public static final Integer DEFAULT_PAGE = 0;
    public static final Integer INVALID_PAGE = -1;
    public static final Integer DEFAULT_SIZE = 10;
    public static final Integer SIZE_ONE = 1;
}
