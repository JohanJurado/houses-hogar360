package com.pragma.hogar360_microservice_house.infraestructure.mappers;

import com.pragma.hogar360_microservice_house.domain.model.CityModel;
import com.pragma.hogar360_microservice_house.domain.model.DepartmentModel;
import com.pragma.hogar360_microservice_house.infraestructure.entities.CityEntity;
import com.pragma.hogar360_microservice_house.infraestructure.entities.DepartmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ILocationEntityMapper {

    @Mapping(target = "departmentModelList", source="departmentEntityList")
    CityModel entityToModelCity(CityEntity cityEntity);
    default Optional<CityModel> entityOptionalToModelOptionalCity(Optional<CityEntity> cityEntity){
        return cityEntity.map(this::entityToModelCity);
    }
    @Mapping(target = "departmentEntityList", source="departmentModelList")
    CityEntity modelToEntityCity(CityModel cityModel);

    @Mapping(target = "id", source="id")
    @Mapping(target = "name", source="name")
    @Mapping(target = "description", source="description")
    @Mapping(target = "city", source="city")
    DepartmentModel entityToModelDepartment(DepartmentEntity departmentEntity);
    default Optional<DepartmentModel> entityOptionalToModelOptionalDepartment(Optional<DepartmentEntity> departmentEntity){
        return departmentEntity.map(this::entityToModelDepartment);
    }
    DepartmentEntity modelToEntityDepartment(DepartmentModel departmentModel);
}
