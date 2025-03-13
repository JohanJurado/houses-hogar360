package com.pragma.hogar360_microservice_house.category.application.services.impl;

import com.pragma.hogar360_microservice_house.category.application.dtos.request.SaveCategoryRequest;
import com.pragma.hogar360_microservice_house.category.application.dtos.response.CategoryResponse;
import com.pragma.hogar360_microservice_house.category.application.dtos.response.SaveCategoryResponse;
import com.pragma.hogar360_microservice_house.category.application.mappers.ICategoryDtoMapper;
import com.pragma.hogar360_microservice_house.category.application.services.ICategoryService;
import com.pragma.hogar360_microservice_house.category.domain.ports.in.ICategoryServicePort;
import com.pragma.hogar360_microservice_house.commons.configurations.utils.Constants;
import com.pragma.hogar360_microservice_house.commons.configurations.utils.Pagination.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {
    private final ICategoryServicePort categoryServicePort;
    private final ICategoryDtoMapper categoryDtoMapper;

    @Override
    public SaveCategoryResponse save(SaveCategoryRequest saveCategoryRequest) {
        categoryServicePort.save(categoryDtoMapper.requestToModel(saveCategoryRequest));
        return new SaveCategoryResponse(Constants.SAVE_CATEGORY_RESPONSE_MESSAGE, LocalDateTime.now());
    }

    @Override
    public Pagination<CategoryResponse> getCategories(String nameCategory, Integer page, Integer size, boolean orderAsc) {
        return categoryDtoMapper.modelPaginationToResponsePagination(categoryServicePort.getCategories(nameCategory, page, size, orderAsc));
    }
}
