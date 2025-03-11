package com.pragma.hogar360_microservice_house.category.application.services;

import com.pragma.hogar360_microservice_house.category.application.dtos.request.SaveCategoryRequest;
import com.pragma.hogar360_microservice_house.category.application.dtos.response.CategoryResponse;
import com.pragma.hogar360_microservice_house.category.application.dtos.response.SaveCategoryResponse;

import java.util.List;

public interface ICategoryService {
    SaveCategoryResponse save(SaveCategoryRequest saveCategoryRequest);
    List<CategoryResponse> get(String nameCategory, Integer page, Integer size, boolean orderAsc);
}
