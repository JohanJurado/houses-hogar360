package com.pragma.hogar360_microservice_house.category.application.services.impl;

import com.pragma.hogar360_microservice_house.category.application.dtos.request.SaveCategoryRequest;
import com.pragma.hogar360_microservice_house.category.application.dtos.response.CategoryResponse;
import com.pragma.hogar360_microservice_house.category.application.dtos.response.SaveCategoryResponse;
import com.pragma.hogar360_microservice_house.category.application.mappers.ICategoryDtoMapper;
import com.pragma.hogar360_microservice_house.category.application.services.ICategoryService;
import com.pragma.hogar360_microservice_house.category.domain.ports.in.ICategoryServicePort;
import com.pragma.hogar360_microservice_house.commons.configurations.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
    public List<CategoryResponse> get(String nameCategory, Integer page, Integer size, boolean orderAsc) {
        return categoryDtoMapper.modelListToResponseList(categoryServicePort.get(nameCategory, page, size, orderAsc));
    }
}
