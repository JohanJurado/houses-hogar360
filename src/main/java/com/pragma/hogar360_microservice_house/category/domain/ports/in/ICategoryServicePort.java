package com.pragma.hogar360_microservice_house.category.domain.ports.in;

import com.pragma.hogar360_microservice_house.category.domain.model.CategoryModel;

public interface ICategoryServicePort {
    void save(CategoryModel categoryModel);
}
