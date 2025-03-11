package com.pragma.hogar360_microservice_house.category.domain.ports.out;

import com.pragma.hogar360_microservice_house.category.domain.model.CategoryModel;

public interface ICategoryPersistencePort {
    void save(CategoryModel categoryModel);
    CategoryModel findByName(String name);
}

