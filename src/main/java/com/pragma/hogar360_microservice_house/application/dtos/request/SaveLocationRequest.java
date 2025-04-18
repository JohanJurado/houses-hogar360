package com.pragma.hogar360_microservice_house.application.dtos.request;

public record SaveLocationRequest(
        String neighborhood,
        String nameCity,
        String descriptionCity,
        String nameDepartment,
        String descriptionDepartment) {
}
