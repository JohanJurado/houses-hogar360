package com.pragma.hogar360_microservice_house.application.mappers;

import com.pragma.hogar360_microservice_house.application.dtos.request.SaveLocationRequest;
import com.pragma.hogar360_microservice_house.domain.model.CityModel;
import com.pragma.hogar360_microservice_house.domain.model.DepartmentModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ILocationDtoMapper {

    @Mapping(target = "name", source="nameCity")
    @Mapping(target = "description", source="descriptionCity")
    CityModel requestToModelCity(SaveLocationRequest saveLocationRequest);

    @Mapping(target = "name", source="nameDepartment")
    @Mapping(target = "description", source="descriptionDepartment")
    DepartmentModel requestToModelDepartment(SaveLocationRequest saveLocationRequest);
}
