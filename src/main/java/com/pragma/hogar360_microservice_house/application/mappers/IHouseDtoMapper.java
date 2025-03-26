package com.pragma.hogar360_microservice_house.application.mappers;

import com.pragma.hogar360_microservice_house.application.dtos.request.SaveHouseRequest;
import com.pragma.hogar360_microservice_house.application.dtos.response.HouseResponse;
import com.pragma.hogar360_microservice_house.domain.model.HouseModel;
import com.pragma.hogar360_microservice_house.domain.util.pagination.Pagination;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IHouseDtoMapper {

    @Mapping(target = "cityModel.name", source="cityName")
    @Mapping(target = "cityModel.departmentModel.name", source="departmentName")
    @Mapping(target = "categoryModel.name", source="categoryName")
    HouseModel requestToModel(SaveHouseRequest saveHouseRequest);

    @Mapping(target = "cityName", source="cityModel.name")
    @Mapping(target = "departmentName", source="cityModel.departmentModel.name")
    @Mapping(target = "categoryName", source="categoryModel.name")
    HouseResponse modelToResponse(HouseModel houseModel);

    default Pagination<HouseResponse> modelPaginationToResponsePagination(Pagination<HouseModel> houseModelPagination){
        if (houseModelPagination == null) {
            return null;
        }

        List<HouseResponse> content = houseModelPagination.getContent()
                .stream()
                .map(this::modelToResponse)
                .toList();

        return new Pagination<>(
                content,
                houseModelPagination.getPageNumber(),
                houseModelPagination.getPageSize(),
                houseModelPagination.getTotalPages(),
                houseModelPagination.isLast()
        );
    }
}
