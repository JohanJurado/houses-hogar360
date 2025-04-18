package com.pragma.hogar360_microservice_house.infraestructure.mappers;

import com.pragma.hogar360_microservice_house.domain.model.DepartmentModel;
import com.pragma.hogar360_microservice_house.infraestructure.entities.DepartmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface IDepartmentEntityMapper {

    @Mapping(target = "id", source="id")
    @Mapping(target = "name", source="name")
    @Mapping(target = "description", source="description")
    DepartmentEntity modelToEntity(DepartmentModel departmentModel);

    @Mapping(target = "id", source="id")
    @Mapping(target = "name", source="name")
    @Mapping(target = "description", source="description")
    DepartmentModel entityToModel(DepartmentEntity departmentEntity);

    default Optional<DepartmentModel> entityOptionalToModelOptional(Optional<DepartmentEntity> departmentEntityOptional){
        return departmentEntityOptional.map(this::entityToModel);
    }
}
