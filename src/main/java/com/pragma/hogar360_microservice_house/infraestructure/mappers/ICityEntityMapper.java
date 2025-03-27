package com.pragma.hogar360_microservice_house.infraestructure.mappers;

import com.pragma.hogar360_microservice_house.domain.model.CityModel;
import com.pragma.hogar360_microservice_house.infraestructure.entities.CityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ICityEntityMapper {


    @Mapping(target = "id", source="id")
    @Mapping(target = "name", source="name")
    @Mapping(target = "description", source="description")
    @Mapping(target = "departmentEntity", source="departmentModel")
    CityEntity modelToEntity(CityModel cityModel);

    @Mapping(target = "id", source="id")
    @Mapping(target = "name", source="name")
    @Mapping(target = "description", source="description")
    @Mapping(target = "departmentModel", source="departmentEntity")
    CityModel entityToModel(CityEntity cityEntity);

    default Optional<CityModel> entityOptionalToModelOptional(Optional<CityEntity> cityEntityOptional){
        return cityEntityOptional.map(this::entityToModel);
    }
}
