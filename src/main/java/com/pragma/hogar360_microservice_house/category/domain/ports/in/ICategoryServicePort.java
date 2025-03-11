package com.pragma.hogar360_microservice_house.category.domain.ports.in;

import com.pragma.hogar360_microservice_house.category.domain.model.CategoryModel;

import java.util.List;

public interface ICategoryServicePort {
    void save(CategoryModel categoryModel);
    List<CategoryModel> get(String nameCategory, Integer page, Integer size, boolean orderAsc);
}
