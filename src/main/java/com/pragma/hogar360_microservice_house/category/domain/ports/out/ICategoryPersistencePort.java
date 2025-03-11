package com.pragma.hogar360_microservice_house.category.domain.ports.out;

import com.pragma.hogar360_microservice_house.category.domain.model.CategoryModel;

import java.util.List;

public interface ICategoryPersistencePort {
    void save(CategoryModel categoryModel);
    CategoryModel findByName(String name);
    List<CategoryModel> getAllCategories(Integer page, Integer size, boolean orderAsc);
}

