package com.pragma.hogar360_microservice_house.application.services.impl;

import com.pragma.hogar360_microservice_house.application.dtos.request.SaveCategoryRequest;
import com.pragma.hogar360_microservice_house.application.dtos.response.CategoryResponse;
import com.pragma.hogar360_microservice_house.application.dtos.response.SaveDtoResponses;
import com.pragma.hogar360_microservice_house.application.mappers.ICategoryDtoMapper;
import com.pragma.hogar360_microservice_house.application.services.ICategoryService;
import com.pragma.hogar360_microservice_house.application.utils.ApplicationConstants;
import com.pragma.hogar360_microservice_house.domain.ports.in.ICategoryServicePort;
import com.pragma.hogar360_microservice_house.domain.util.pagination.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {
    private final ICategoryServicePort categoryServicePort;
    private final ICategoryDtoMapper categoryDtoMapper;

    @Override
    public SaveDtoResponses save(SaveCategoryRequest saveCategoryRequest) {
        categoryServicePort.save(categoryDtoMapper.requestToModel(saveCategoryRequest));
        return new SaveDtoResponses(ApplicationConstants.SAVE_CATEGORY_RESPONSE_MESSAGE, LocalDateTime.now());
    }

    @Override
    public Pagination<CategoryResponse> getCategories(String nameCategory, Integer page, Integer size, boolean orderAsc) {
        return categoryDtoMapper.modelPaginationToResponsePagination(categoryServicePort.getCategories(nameCategory, page, size, orderAsc));
    }
}
