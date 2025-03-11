package com.pragma.hogar360_microservice_house.category.application.mappers;

import com.pragma.hogar360_microservice_house.category.application.dtos.request.SaveCategoryRequest;
import com.pragma.hogar360_microservice_house.category.application.dtos.response.SaveCategoryResponse;
import com.pragma.hogar360_microservice_house.category.domain.model.CategoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICategoryDtoMapper {

    CategoryModel requestToModel(SaveCategoryRequest saveCategoryRequest);
    SaveCategoryResponse modelToResponse(CategoryModel categoryModel);

}
