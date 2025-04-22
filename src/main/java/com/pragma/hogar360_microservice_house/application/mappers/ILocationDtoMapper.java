package com.pragma.hogar360_microservice_house.application.mappers;

import com.pragma.hogar360_microservice_house.application.dtos.request.SaveLocationRequest;
import com.pragma.hogar360_microservice_house.application.dtos.response.LocationResponse;
import com.pragma.hogar360_microservice_house.domain.model.LocationModel;
import com.pragma.hogar360_microservice_house.domain.util.pagination.Pagination;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ILocationDtoMapper {

    @Mapping(target = "neighborhood", source="neighborhood")
    @Mapping(target = "cityModel.name", source="nameCity")
    @Mapping(target = "cityModel.description", source="descriptionCity")
    @Mapping(target = "cityModel.departmentModel.name", source="nameDepartment")
    @Mapping(target = "cityModel.departmentModel.description", source="descriptionDepartment")
    LocationModel requestToModel(SaveLocationRequest saveLocationRequest);

    @Mapping(target = "neighborhood", source="neighborhood")
    @Mapping(target = "nameCity", source="cityModel.name")
    @Mapping(target = "descriptionCity", source="cityModel.description")
    @Mapping(target = "nameDepartment", source="cityModel.departmentModel.name")
    @Mapping(target = "descriptionDepartment", source="cityModel.departmentModel.description")
    LocationResponse modelToResponse(LocationModel locationModel);

    default Pagination<LocationResponse> modelPaginationToResponsePagination(Pagination<LocationModel> locationModelPagination){
        if (locationModelPagination == null) {
            return null;
        }

        List<LocationResponse> content = locationModelPagination.getContent()
                .stream()
                .map(this::modelToResponse)
                .toList();

        return new Pagination<>(
                content,
                locationModelPagination.getPageNumber(),
                locationModelPagination.getPageSize(),
                locationModelPagination.getTotalPages(),
                locationModelPagination.isLast()
        );
    }
}
