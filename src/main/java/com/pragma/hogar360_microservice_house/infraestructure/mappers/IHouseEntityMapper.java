package com.pragma.hogar360_microservice_house.infraestructure.mappers;

import com.pragma.hogar360_microservice_house.domain.model.HouseModel;
import com.pragma.hogar360_microservice_house.infraestructure.entities.HouseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IHouseEntityMapper {

    @Mapping(target = "cityModel", source="cityEntity")
    @Mapping(target = "cityModel.departmentModel", source="cityEntity.departmentEntity")
    @Mapping(target = "categoryModel", source="categoryEntity")
    HouseModel entityToModel(HouseEntity houseEntity);

    @Mapping(target = "cityEntity", source="cityModel")
    @Mapping(target = "cityEntity.departmentEntity", source="cityModel.departmentModel")
    @Mapping(target = "categoryEntity", source="categoryModel")
    HouseEntity modelToEntity(HouseModel houseModel);

    default List<HouseModel> entityListToModelList(List<HouseEntity> houseEntityList){
        return houseEntityList.stream()
                .map(this::entityToModel)
                .toList();
    }
}
