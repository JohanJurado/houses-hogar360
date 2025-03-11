package com.pragma.hogar360_microservice_house.category.application.services;

import com.pragma.hogar360_microservice_house.category.application.dtos.request.SaveCategoryRequest;
import com.pragma.hogar360_microservice_house.category.application.dtos.response.SaveCategoryResponse;

public interface ICategoryService {
    SaveCategoryResponse save(SaveCategoryRequest saveCategoryRequest);
}
