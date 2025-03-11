package com.pragma.hogar360_microservice_house.category.domain.usecases;

import com.pragma.hogar360_microservice_house.category.domain.exceptions.CategoryAlreadyExistsException;
import com.pragma.hogar360_microservice_house.category.domain.model.CategoryModel;
import com.pragma.hogar360_microservice_house.category.domain.ports.in.ICategoryServicePort;
import com.pragma.hogar360_microservice_house.category.domain.ports.out.ICategoryPersistencePort;

public class CategoryUseCase implements ICategoryServicePort {

    private ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }


    @Override
    public void save(CategoryModel categoryModel) {
        if (categoryPersistencePort.findByName(categoryModel.getName()) != null)
            throw new CategoryAlreadyExistsException();
        categoryPersistencePort.save(categoryModel);
    }
}
