package com.pragma.hogar360_microservice_house.category.utils;


import com.pragma.hogar360_microservice_house.category.domain.model.CategoryModel;

public class TestDataCategory {

    public static CategoryModel getCategory =
            new CategoryModel(1L, "Category 1", "Description 1");

    public static final CategoryModel getCategoryMaxName(){
        CategoryModel category = getCategory;
        category.setName("1".repeat(51));
        return category;
    }

    public static final CategoryModel getCategoryMaxDescription(){
        CategoryModel category = getCategory;
        category.setDescription("1".repeat(91));
        return category;
    }

    public static final CategoryModel getCategoryNameNull(){
        CategoryModel category = getCategory;
        category.setName(null);
        return category;
    }

    public static final CategoryModel getCategoryNameBlank(){
        CategoryModel category = getCategory;
        category.setName("");
        return category;
    }

    public static final CategoryModel getCategoryDescriptionNull(){
        CategoryModel category = getCategory;
        category.setDescription(null);
        return category;
    }

    public static final CategoryModel getCategoryDescriptionBlank(){
        CategoryModel category = getCategory;
        category.setDescription("");
        return category;
    }
}
