package com.pragma.hogar360_microservice_house.application.mappers;

import com.pragma.hogar360_microservice_house.application.dtos.request.SaveCategoryRequest;
import com.pragma.hogar360_microservice_house.application.dtos.response.CategoryResponse;
import com.pragma.hogar360_microservice_house.domain.model.CategoryModel;
import com.pragma.hogar360_microservice_house.domain.util.pagination.Pagination;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICategoryDtoMapper {

    CategoryModel requestToModel(SaveCategoryRequest saveCategoryRequest);
    CategoryResponse modelToResponse(CategoryModel categoryModel);

    default Pagination<CategoryResponse> modelPaginationToResponsePagination(Pagination<CategoryModel> categoryModels) {
        if (categoryModels == null) {
            return null;
        }

        List<CategoryResponse> content = categoryModels.getContent()
                .stream()
                .map(this::modelToResponse)
                .toList();

        return new Pagination<>(
                content,
                categoryModels.getPageNumber(),
                categoryModels.getPageSize(),
                categoryModels.getTotalPages(),
                categoryModels.isLast()
        );
    }

}
