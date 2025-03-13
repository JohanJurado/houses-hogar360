package com.pragma.hogar360_microservice_house.category.infraestructure.exceptionshandler;

public class ExceptionConstants {
    private ExceptionConstants(){}

    public static final String NAME_MAX_SIZE_MESSAGE = "The name of category cannot exceed 50 characters";
    public static final String DESCRIPTION_MAX_SIZE_MESSAGE = "The description of category cannot exceed 90 characters";
    public static final String CATEGORY_EXIST_MESSAGE = "Category already exists";
    public static final String CATEGORY_NOT_FOUND_MESSAGE = "Category not found";
}
