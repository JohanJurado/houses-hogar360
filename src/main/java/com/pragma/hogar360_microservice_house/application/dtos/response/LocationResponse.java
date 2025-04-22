package com.pragma.hogar360_microservice_house.application.dtos.response;

public record LocationResponse(
        Long id,
        String neighborhood,
        String nameCity,
        String descriptionCity,
        String nameDepartment,
        String descriptionDepartment) {
}
