package com.pragma.hogar360_microservice_house.utils.testdata;

import com.pragma.hogar360_microservice_house.domain.model.CategoryModel;

import java.util.ArrayList;
import java.util.List;

import static com.pragma.hogar360_microservice_house.utils.constants.CategoryTestConstants.*;

public class TestDataCategory {

    public static CategoryModel getCategory(){
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setId(ID_CATEGORY_ONE);
        categoryModel.setName(VALID_NAME_CATEGORY);
        categoryModel.setDescription(VALID_DESCRIPTION_CATEGORY);
        return categoryModel;
    }

    public static CategoryModel getCategoryMaxName(){
        CategoryModel category = getCategory();
        category.setName(INVALID_NAME_CATEGORY);
        return category;
    }

    public static CategoryModel getCategoryMaxDescription(){
        CategoryModel category = getCategory();
        category.setDescription(INVALID_DESCRIPTION_CATEGORY);
        return category;
    }

    public static CategoryModel getCategoryNameNull(){
        CategoryModel category = getCategory();
        category.setName(NULL_STRING_ATTRIBUTE_CATEGORY);
        return category;
    }

    public static CategoryModel getCategoryNameBlank(){
        CategoryModel category = getCategory();
        category.setName(BLANK_STRING_ATTRIBUTE_CATEGORY);
        return category;
    }

    public static CategoryModel getCategoryDescriptionNull(){
        CategoryModel category = getCategory();
        category.setDescription(NULL_STRING_ATTRIBUTE_CATEGORY);
        return category;
    }

    public static CategoryModel getCategoryDescriptionBlank(){
        CategoryModel category = getCategory();
        category.setDescription(BLANK_STRING_ATTRIBUTE_CATEGORY);
        return category;
    }

    public static List<CategoryModel> getCategoryModels(){
        CategoryModel category = getCategory();
        List<CategoryModel> categoryModelList = new ArrayList<>();
        categoryModelList.add(category);
        return categoryModelList;
    }
}


