package com.pragma.hogar360_microservice_house.category.infraestructure.mappers;

import com.pragma.hogar360_microservice_house.category.domain.model.CategoryModel;
import com.pragma.hogar360_microservice_house.category.infraestructure.entities.CategoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoryEntityMapper {

    CategoryModel entityToModel(CategoryEntity categoryEntity);
    CategoryEntity modelToEntity(CategoryModel categoryModel);
    List<CategoryModel> entityListToModelList(List<CategoryEntity> categoryEntityList);
}
