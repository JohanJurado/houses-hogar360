package com.pragma.hogar360_microservice_house.infraestructure.mappers;

import com.pragma.hogar360_microservice_house.domain.model.CityModel;
import com.pragma.hogar360_microservice_house.domain.model.DepartmentModel;
import com.pragma.hogar360_microservice_house.infraestructure.entities.CityEntity;
import com.pragma.hogar360_microservice_house.infraestructure.entities.DepartmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ILocationEntityMapper {

    DepartmentModel entityToModelDepartment(DepartmentEntity departmentEntity);
    default Optional<DepartmentModel> entityOptionalToModelOptionalDepartment(Optional<DepartmentEntity> departmentEntity){
        return departmentEntity.map(this::entityToModelDepartment);
    }
    DepartmentEntity modelToEntityDepartment(DepartmentModel departmentModel);
    default List<DepartmentModel> entityListToModelListDepartment(List<DepartmentEntity> departmentEntityList){
        return departmentEntityList.stream()
                .map(this::entityToModelDepartment)
                .toList();
    }

    @Mapping(target = "id", source="id")
    @Mapping(target = "name", source="name")
    @Mapping(target = "description", source="description")
    @Mapping(target = "departmentModel", source="departmentEntity")
    CityModel entityToModelCity(CityEntity cityEntity);

    @Mapping(target = "id", source="id")
    @Mapping(target = "name", source="name")
    @Mapping(target = "description", source="description")
    @Mapping(target = "departmentEntity", source="departmentModel")
    CityEntity modelToEntityCity(CityModel cityModel);

    default List<CityModel> entityListToModelListCity(List<CityEntity> cityEntityList){
        return cityEntityList.stream()
                .map(this::entityToModelCity)
                .toList();
    }

    default Optional<List<CityModel>> entityOptionalListToModelOptionalListCity(Optional<List<CityEntity>> cityEntity){
        return cityEntity.map(this::entityListToModelListCity);
    }
}
