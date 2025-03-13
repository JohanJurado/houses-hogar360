package com.pragma.hogar360_microservice_house.category.domain.ports.in;

import com.pragma.hogar360_microservice_house.category.domain.model.CategoryModel;
import com.pragma.hogar360_microservice_house.commons.configurations.utils.Pagination.Pagination;

import java.util.List;

public interface ICategoryServicePort {
    void save(CategoryModel categoryModel);
    Pagination<CategoryModel> getCategories(String nameCategory, Integer page, Integer size, boolean orderAsc);
}
