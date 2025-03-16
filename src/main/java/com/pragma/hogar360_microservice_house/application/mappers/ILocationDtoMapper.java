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

    @Mapping(target = "nameCity", source="city.name")
    @Mapping(target = "descriptionCity", source="city.description")
    @Mapping(target = "nameDepartment", source="name")
    @Mapping(target = "descriptionDepartment", source="description")
    LocationResponse modelToResponseDepartment(DepartmentModel departmentModel);

    default Pagination<LocationResponse> modelToResponse(Pagination<DepartmentModel> departmentModelPagination){
        if (departmentModelPagination == null) {
            return null;
        }

        List<LocationResponse> content = departmentModelPagination.getContent()
                .stream()
                .map(this::modelToResponseDepartment)
                .toList();

        return new Pagination<>(
                content,
                departmentModelPagination.getPageNumber(),
                departmentModelPagination.getPageSize(),
                departmentModelPagination.getTotalElements()
        );
    }
}
