package com.pragma.hogar360_microservice_house.category.domain.ports.out;

import com.pragma.hogar360_microservice_house.category.domain.model.CategoryModel;

import java.util.List;
import java.util.Optional;

public interface ICategoryPersistencePort {
    void save(CategoryModel categoryModel);
    Optional<CategoryModel> findByName(String name);
    List<CategoryModel> getAllCategories();
}

