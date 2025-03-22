package com.pragma.hogar360_microservice_house.application.mappers;

import com.pragma.hogar360_microservice_house.application.dtos.request.SaveLocationRequest;
import com.pragma.hogar360_microservice_house.application.dtos.response.LocationResponse;
import com.pragma.hogar360_microservice_house.domain.model.CityModel;
import com.pragma.hogar360_microservice_house.domain.model.DepartmentModel;
import com.pragma.hogar360_microservice_house.domain.util.pagination.Pagination;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ILocationDtoMapper {

    @Mapping(target = "name", source="nameCity")
    @Mapping(target = "description", source="descriptionCity")
    CityModel requestToModelCity(SaveLocationRequest saveLocationRequest);

    @Mapping(target = "name", source="nameDepartment")
    @Mapping(target = "description", source="descriptionDepartment")
    DepartmentModel requestToModelDepartment(SaveLocationRequest saveLocationRequest);

    @Mapping(target = "nameCity", source="name")
    @Mapping(target = "descriptionCity", source="description")
    @Mapping(target = "nameDepartment", source="departmentModel.name")
    @Mapping(target = "descriptionDepartment", source="departmentModel.description")
    LocationResponse modelToResponseCity(CityModel cityModel);

    default Pagination<LocationResponse> modelToResponse(Pagination<CityModel> cityModelPagination){
        if (cityModelPagination == null) {
            return null;
        }

        List<LocationResponse> content = cityModelPagination.getContent()
                .stream()
                .map(this::modelToResponseCity)
                .toList();

        return new Pagination<>(
                content,
                cityModelPagination.getPageNumber(),
                cityModelPagination.getPageSize(),
                cityModelPagination.getTotalPages(),
                cityModelPagination.isLast()
        );
    }
}
