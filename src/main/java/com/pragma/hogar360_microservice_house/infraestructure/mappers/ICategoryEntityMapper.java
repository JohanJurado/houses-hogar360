package com.pragma.hogar360_microservice_house.infraestructure.mappers;

import com.pragma.hogar360_microservice_house.domain.model.CategoryModel;
import com.pragma.hogar360_microservice_house.infraestructure.entities.CategoryEntity;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ICategoryEntityMapper {

    CategoryModel entityToModel(CategoryEntity categoryEntity);
    default Optional<CategoryModel> entityOptionalToModelOptional(Optional<CategoryEntity> categoryEntity){
        return categoryEntity.map(this::entityToModel);
    }
    CategoryEntity modelToEntity(CategoryModel categoryModel);
    List<CategoryModel> entityListToModelList(List<CategoryEntity> categoryEntityList);
}
