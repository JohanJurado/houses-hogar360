package com.pragma.hogar360_microservice_house.application.dtos.request;

import java.time.LocalDate;

public record SaveHouseRequest(
        String name,
        String description,
        Long bedroomCount,
        Long bathroomCount,
        Double price,
        LocalDate activePublicationDate,
        String cityModelName,
        String departmentModelName,
        String categoryModelName
) {
}
