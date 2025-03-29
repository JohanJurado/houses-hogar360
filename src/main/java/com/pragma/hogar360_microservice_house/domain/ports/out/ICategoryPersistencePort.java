package com.pragma.hogar360_microservice_house.domain.ports.out;

import com.pragma.hogar360_microservice_house.domain.model.CategoryModel;

import java.util.List;
import java.util.Optional;

public interface ICategoryPersistencePort {
    void save(CategoryModel categoryModel);
    Optional<CategoryModel> findByName(String name);

    List<CategoryModel> findAllByName(String name);
}

