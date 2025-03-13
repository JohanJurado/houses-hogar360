package com.pragma.hogar360_microservice_house.application.services;

import com.pragma.hogar360_microservice_house.application.dtos.request.SaveCategoryRequest;
import com.pragma.hogar360_microservice_house.application.dtos.response.CategoryResponse;
import com.pragma.hogar360_microservice_house.application.dtos.response.SaveCategoryResponse;
import com.pragma.hogar360_microservice_house.domain.util.pagination.Pagination;

public interface ICategoryService {
    SaveCategoryResponse save(SaveCategoryRequest saveCategoryRequest);
    Pagination<CategoryResponse> getCategories(String nameCategory, Integer page, Integer size, boolean orderAsc);
}
