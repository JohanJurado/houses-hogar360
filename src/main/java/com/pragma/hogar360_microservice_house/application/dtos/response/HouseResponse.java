package com.pragma.hogar360_microservice_house.application.dtos.response;

public record HouseResponse(
        String name,
        String description,
        Long bedroomCount,
        Long bathroomCount,
        Double price,
        String publicationStatus,
        String neighborhood,
        String cityName,
        String departmentName,
        String categoryName
) {
}
