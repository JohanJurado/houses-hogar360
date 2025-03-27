package com.pragma.hogar360_microservice_house.domain.usecases;

import com.pragma.hogar360_microservice_house.domain.exceptions.*;
import com.pragma.hogar360_microservice_house.domain.model.CategoryModel;
import com.pragma.hogar360_microservice_house.domain.ports.in.ICategoryServicePort;
import com.pragma.hogar360_microservice_house.domain.ports.out.ICategoryPersistencePort;
import com.pragma.hogar360_microservice_house.domain.util.constants.DomainConstants;
import com.pragma.hogar360_microservice_house.domain.util.pagination.Pagination;
import com.pragma.hogar360_microservice_house.domain.util.validations.GlobalValidations;

import java.util.Comparator;
import java.util.List;

import static com.pragma.hogar360_microservice_house.domain.util.validations.CategoryValidation.*;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }


    @Override
    public void save(CategoryModel categoryModel) {

        validationByCategoryAttributes(categoryModel);
        toUpperStringCategoryAttributes(categoryModel);

        if (categoryPersistencePort.findByName(categoryModel.getName()).isPresent()){
            throw new CategoryAlreadyExistsException();
        }

        categoryPersistencePort.save(categoryModel);
    }

    @Override
    public Pagination<CategoryModel> getCategories(String nameCategory, Integer page, Integer size, boolean orderAsc) {

        if (nameCategory.isBlank()) {
            List<CategoryModel> categoryModels = categoryPersistencePort.getAllCategories();

            return new Pagination<>(categoryModels, page, size, Comparator.comparing(CategoryModel::getName), orderAsc);
        }

        List<CategoryModel> categoryFoundList =
                List.of(categoryPersistencePort.findByName(nameCategory).orElseThrow(CategoryNotFoundException::new));

        return new Pagination<>(categoryFoundList, page, size, Comparator.comparing(CategoryModel::getName), orderAsc);
    }
}
