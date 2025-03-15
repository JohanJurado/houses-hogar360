package com.pragma.hogar360_microservice_house.domain.ports.in;

import com.pragma.hogar360_microservice_house.domain.model.CategoryModel;
import com.pragma.hogar360_microservice_house.domain.util.pagination.Pagination;

public interface ICategoryServicePort {
    void save(CategoryModel categoryModel);
    Pagination<CategoryModel> getCategories(String nameCategory, Integer page, Integer size, boolean orderAsc);
}
