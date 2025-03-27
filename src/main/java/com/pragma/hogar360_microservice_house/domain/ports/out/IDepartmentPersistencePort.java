package com.pragma.hogar360_microservice_house.domain.ports.out;

import com.pragma.hogar360_microservice_house.domain.model.DepartmentModel;

import java.util.Optional;

public interface IDepartmentPersistencePort {
    DepartmentModel save(DepartmentModel departmentModel);
    Optional<DepartmentModel> findByName(String nameDepartment);
}
