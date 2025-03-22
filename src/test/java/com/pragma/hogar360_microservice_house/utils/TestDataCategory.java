package com.pragma.hogar360_microservice_house.utils;


import com.pragma.hogar360_microservice_house.domain.model.CategoryModel;

import java.util.ArrayList;
import java.util.List;

public class TestDataCategory {

    public static CategoryModel getCategory(){
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setId(1L);
        categoryModel.setName("Category 1");
        categoryModel.setDescription("Description 1");
        return categoryModel;
    }

    public static CategoryModel getCategoryMaxName(){
        CategoryModel category = getCategory();
        category.setName("1".repeat(51));
        return category;
    }

    public static CategoryModel getCategoryMaxDescription(){
        CategoryModel category = getCategory();
        category.setDescription("1".repeat(91));
        return category;
    }

    public static CategoryModel getCategoryNameNull(){
        CategoryModel category = getCategory();
        category.setName(null);
        return category;
    }

    public static CategoryModel getCategoryNameBlank(){
        CategoryModel category = getCategory();
        category.setName("");
        return category;
    }

    public static CategoryModel getCategoryDescriptionNull(){
        CategoryModel category = getCategory();
        category.setDescription(null);
        return category;
    }

    public static CategoryModel getCategoryDescriptionBlank(){
        CategoryModel category = getCategory();
        category.setDescription("");
        return category;
    }

    public static String getNameCategory(){
        CategoryModel category = getCategory();
        return category.getName();
    }

    public static List<CategoryModel> getCategoryModels(){
        CategoryModel category = getCategory();
        List<CategoryModel> categoryModelList = new ArrayList<>();
        categoryModelList.add(category);
        return categoryModelList;
    }

    public static final Integer PAGE_PAGINATION = 0;
    public static final Integer PAGE_NOT_FOUND_PAGINATION = 100;
    public static final String NAME_CATEGORY_BLANK_PAGINATION = "";
    public static final Integer SIZE_PAGINATION = 10;
    public static final boolean ORDER_ASC_PAGINATION = true;
    public static final boolean ORDER_DESC_PAGINATION = false;
}


