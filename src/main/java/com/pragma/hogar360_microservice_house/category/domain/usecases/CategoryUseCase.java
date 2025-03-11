package com.pragma.hogar360_microservice_house.category.domain.usecases;

import com.pragma.hogar360_microservice_house.category.domain.exceptions.CategoryAlreadyExistsException;
import com.pragma.hogar360_microservice_house.category.domain.exceptions.CategoryNotFoundException;
import com.pragma.hogar360_microservice_house.category.domain.model.CategoryModel;
import com.pragma.hogar360_microservice_house.category.domain.ports.in.ICategoryServicePort;
import com.pragma.hogar360_microservice_house.category.domain.ports.out.ICategoryPersistencePort;

import java.util.List;

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

    @Override
    public List<CategoryModel> get(String nameCategory, Integer page, Integer size, boolean orderAsc) {
        if (!nameCategory.isBlank()){
            CategoryModel categoryFound = categoryPersistencePort.findByName(nameCategory);

            if (categoryFound == null) throw new CategoryNotFoundException();
            else return List.of(categoryFound);
        }
        return categoryPersistencePort.getAllCategories(page, size, orderAsc);
    }
}
