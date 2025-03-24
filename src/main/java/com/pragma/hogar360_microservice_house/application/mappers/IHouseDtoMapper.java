package com.pragma.hogar360_microservice_house.application.mappers;

import com.pragma.hogar360_microservice_house.application.dtos.request.SaveHouseRequest;
import com.pragma.hogar360_microservice_house.domain.model.HouseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IHouseDtoMapper {

    @Mapping(target = "cityModel.name", source="cityModelName")
    @Mapping(target = "cityModel.departmentModel.name", source="departmentModelName")
    @Mapping(target = "categoryModel.name", source="categoryModelName")
    HouseModel requestToModel(SaveHouseRequest saveHouseRequest);



}
