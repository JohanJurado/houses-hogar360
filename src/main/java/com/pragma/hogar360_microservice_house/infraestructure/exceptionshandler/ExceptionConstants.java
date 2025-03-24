package com.pragma.hogar360_microservice_house.infraestructure.exceptionshandler;

public class ExceptionConstants {
    private ExceptionConstants(){}

    public static final String PAGE_NOT_FOUND_MESSAGE = "Page not found.";

    public static final String CATEGORY_EXIST_MESSAGE = "Category already exists";
    public static final String CATEGORY_NOT_FOUND_MESSAGE = "Category not found";
    public static final String CATEGORY_NAME_MAX_SIZE_MESSAGE = "The name of category cannot exceed 50 characters";
    public static final String CATEGORY_DESCRIPTION_MAX_SIZE_MESSAGE = "The description of category cannot exceed 90 characters";
    public static final String CATEGORY_NAME_CANNOT_BE_NULL_MESSAGE = "The name of category cannot be null or blank";
    public static final String CATEGORY_DESCRIPTION_CANNOT_BE_NULL_MESSAGE = "The description of category cannot be null or blank";

    public static final String LOCATION_EXIST_MESSAGE = "Location already exists";
    public static final String LOCATION_NAME_MAX_SIZE_MESSAGE = "The name of location cannot exceed 50 characters";
    public static final String LOCATION_DESCRIPTION_MAX_SIZE_MESSAGE = "The description of location cannot exceed 120 characters";
    public static final String LOCATION_NOT_FOUND_MESSAGE = "Location not found";
    public static final String LOCATION_ORDER_NOT_FOUND_MESSAGE = "Order location not found";
    public static final String LOCATION_CITY_NAME_CANNOT_BE_NULL_MESSAGE = "The name of city cannot be null or blank";
    public static final String LOCATION_CITY_DESCRIPTION_CANNOT_BE_NULL_MESSAGE = "The description of city cannot be null or blank";
    public static final String LOCATION_DEPARTMENT_NAME_CANNOT_BE_NULL_MESSAGE = "The name of department cannot be null or blank";
    public static final String LOCATION_DEPARTMENT_DESCRIPTION_CANNOT_BE_NULL_MESSAGE = "The description of department cannot be null or blank";
    public static final String LOCATION_CITY_NOT_FOUND_MESSAGE = "City not found";
    public static final String LOCATION_DEPARTMENT_NOT_FOUND_MESSAGE = "Department not found";

    public static final String HOUSE_ACTIVE_PUBLICATION_DATE_CANNOT_BE_NULL_MESSAGE = "The active publication date of house cannot be null or blank";
    public static final String HOUSE_BATHROOM_COUNT_CANNOT_BE_NULL_MESSAGE = "The bathroom count of house cannot be null or blank";
    public static final String HOUSE_BEDROOM_COUNT_CANNOT_BE_NULL_MESSAGE = "The bedroom count of house cannot be null or blank";
    public static final String HOUSE_CATEGORY_CANNOT_BE_NULL_MESSAGE = "The category of house cannot be null or blank";
    public static final String HOUSE_DESCRIPTION_CANNOT_BE_NULL_MESSAGE = "The description of house cannot be null or blank";
    public static final String HOUSE_LOCATION_CANNOT_BE_NULL_MESSAGE = "The location of house cannot be null or blank";
    public static final String HOUSE_NAME_CANNOT_BE_NULL_MESSAGE = "The name of house cannot be null or blank";
    public static final String HOUSE_PRICE_CANNOT_BE_NULL_MESSAGE = "The price of house cannot be null or blank";

}
