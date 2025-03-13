package com.pragma.hogar360_microservice_house.category.domain.usecases;

import com.pragma.hogar360_microservice_house.category.domain.exceptions.CategoryAlreadyExistsException;
import com.pragma.hogar360_microservice_house.category.domain.exceptions.CategoryNotFoundException;
import com.pragma.hogar360_microservice_house.category.domain.model.CategoryModel;
import com.pragma.hogar360_microservice_house.category.domain.ports.in.ICategoryServicePort;
import com.pragma.hogar360_microservice_house.category.domain.ports.out.ICategoryPersistencePort;
import com.pragma.hogar360_microservice_house.commons.configurations.utils.Pagination.PageNotFound;
import com.pragma.hogar360_microservice_house.commons.configurations.utils.Pagination.Pagination;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }


    @Override
    public void save(CategoryModel categoryModel) {
        if (categoryPersistencePort.findByName(categoryModel.getName()).isPresent()){
            throw new CategoryAlreadyExistsException();
        }
        categoryPersistencePort.save(categoryModel);
    }

    @Override
    public Pagination<CategoryModel> getCategories(String nameCategory, Integer page, Integer size, boolean orderAsc) {
        int fromIndex = (page-1)*size;

        if (nameCategory.isBlank()) {
            List<CategoryModel> categoryModels = categoryPersistencePort.getAllCategories();

            if (orderAsc) {
                categoryModels.sort(Comparator.comparing(CategoryModel::getName));
            } else {
                categoryModels.sort(Comparator.comparing(CategoryModel::getName).reversed());
            }

            int totalElements = categoryModels.size();
            int toIndex = Math.min(fromIndex + size, totalElements);

            if (fromIndex >= totalElements || fromIndex < 0) {
                throw new PageNotFound();
            }

            List<CategoryModel> pageContent = categoryModels.subList(fromIndex, toIndex);
            return new Pagination<>(pageContent, page, size, totalElements);
        }
        Optional<CategoryModel> categoryFound = categoryPersistencePort.findByName(nameCategory);

        List<CategoryModel> categoryFoundList = List.of(categoryFound.orElseThrow(CategoryNotFoundException::new));

        if (fromIndex >= categoryFoundList.size() || fromIndex < 0) {
            throw new PageNotFound();
        }

        return new Pagination<>(categoryFoundList, page, size, categoryFoundList.size());
    }
}
