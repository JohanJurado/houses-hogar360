package com.pragma.hogar360_microservice_house.application.dtos.request.filters;

public record HouseFilterRequest(
        String neighborhood,
        String nameCity,
        String nameDepartment,
        String nameCategory,
        Long bedroomCount,
        Long bathroomCount,
        Double minPrice,
        Double maxPrice
) {
}
