package com.pragma.hogar360_microservice_house.infraestructure.exceptionshandler;

public class ExceptionConstants {
    private ExceptionConstants(){}

    public static final String PAGE_NOT_FOUND_MESSAGE = "Page not found.";

    public static final String CATEGORY_EXIST_MESSAGE = "Category already exists";
    public static final String CATEGORY_NOT_FOUND_MESSAGE = "Category not found";
    public static final String CATEGORY_NAME_MAX_SIZE_MESSAGE = "The name of category cannot exceed 50 characters";
    public static final String CATEGORY_DESCRIPTION_MAX_SIZE_MESSAGE = "The description of category cannot exceed 90 characters";

    public static final String DEPARTMENT_EXIST_MESSAGE = "Department already exists";
    public static final String LOCATION_NAME_MAX_SIZE_MESSAGE = "The name of category cannot exceed 50 characters";
    public static final String LOCATION_DESCRIPTION_MAX_SIZE_MESSAGE = "The description of category cannot exceed 120 characters";
    public static final String LOCATION_NOT_FOUND_MESSAGE = "Location not found";
    public static final String LOCATION_ORDER_NOT_FOUND_MESSAGE = "Order location not found";
}
