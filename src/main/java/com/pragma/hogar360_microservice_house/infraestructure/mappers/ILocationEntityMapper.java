package com.pragma.hogar360_microservice_house.infraestructure.mappers;

import com.pragma.hogar360_microservice_house.domain.model.LocationModel;
import com.pragma.hogar360_microservice_house.infraestructure.entities.LocationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ILocationEntityMapper {

    @Mapping(target = "neighborhood", source="neighborhood")
    @Mapping(target = "cityEntity.id", source="cityModel.id")
    @Mapping(target = "cityEntity.name", source="cityModel.name")
    @Mapping(target = "cityEntity.description", source="cityModel.description")
    @Mapping(target = "cityEntity.departmentEntity.id", source="cityModel.departmentModel.id")
    @Mapping(target = "cityEntity.departmentEntity.name", source="cityModel.departmentModel.name")
    @Mapping(target = "cityEntity.departmentEntity.description", source="cityModel.departmentModel.description")
    LocationEntity modelToEntity(LocationModel locationModel);

    @Mapping(target = "neighborhood", source="neighborhood")
    @Mapping(target = "cityModel.id", source="cityEntity.id")
    @Mapping(target = "cityModel.name", source="cityEntity.name")
    @Mapping(target = "cityModel.description", source="cityEntity.description")
    @Mapping(target = "cityModel.departmentModel.id", source="cityEntity.departmentEntity.id")
    @Mapping(target = "cityModel.departmentModel.name", source="cityEntity.departmentEntity.name")
    @Mapping(target = "cityModel.departmentModel.description", source="cityEntity.departmentEntity.description")
    LocationModel entityToModel(LocationEntity locationEntity);

    default Optional<LocationModel> entityOptionalToModelOptional(Optional<LocationEntity> locationEntityOptional){
        return locationEntityOptional.map(this::entityToModel);
    }

    default List<LocationModel> entityListToModelList(List<LocationEntity> locationEntityList){
        return locationEntityList.stream()
                .map(this::entityToModel)
                .toList();
    }
}
