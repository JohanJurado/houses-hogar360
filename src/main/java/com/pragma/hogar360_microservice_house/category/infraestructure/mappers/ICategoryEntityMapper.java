package com.pragma.hogar360_microservice_house.category.infraestructure.mappers;

import com.pragma.hogar360_microservice_house.category.domain.model.CategoryModel;
import com.pragma.hogar360_microservice_house.category.infraestructure.entities.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ICategoryEntityMapper {
    @Mapping(target = "id", source = "categoryEntity.id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    CategoryModel entityToModel(CategoryEntity categoryEntity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    CategoryEntity modelToEntity(CategoryModel categoryModel);
}
