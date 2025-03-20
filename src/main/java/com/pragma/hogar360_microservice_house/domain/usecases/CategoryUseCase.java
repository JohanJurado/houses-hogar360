package com.pragma.hogar360_microservice_house.domain.usecases;

import com.pragma.hogar360_microservice_house.domain.exceptions.*;
import com.pragma.hogar360_microservice_house.domain.model.CategoryModel;
import com.pragma.hogar360_microservice_house.domain.ports.in.ICategoryServicePort;
import com.pragma.hogar360_microservice_house.domain.ports.out.ICategoryPersistencePort;
import com.pragma.hogar360_microservice_house.domain.util.constants.DomainConstants;
import com.pragma.hogar360_microservice_house.domain.util.pagination.Pagination;
import com.pragma.hogar360_microservice_house.domain.util.validations.Validations;

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
        categoryModel.setName(categoryModel.getName().toUpperCase());
        categoryModel.setDescription(categoryModel.getDescription().toUpperCase());

        Validations.validationByAttributeIsNullOrBlank(categoryModel.getName(), new CategoryNameCannotBeEmptyException());
        Validations.validationByLimitCharacters(categoryModel.getName(), DomainConstants.MAX_NAME_SIZE_CATEGORY, new CategoryNameMaxSizeExceedException());
        Validations.validationByAttributeIsNullOrBlank(categoryModel.getDescription(), new CategoryDescriptionCannotBeEmptyException());
        Validations.validationByLimitCharacters(categoryModel.getDescription(), DomainConstants.MAX_DESCRIPTION_SIZE_CATEGORY, new CategoryDescriptionMaxSizeExceedException());

        if (categoryPersistencePort.findByName(categoryModel.getName()).isPresent()){
            throw new CategoryAlreadyExistsException();
        }

        categoryPersistencePort.save(categoryModel);
    }

    @Override
    public Pagination<CategoryModel> getCategories(String nameCategory, Integer page, Integer size, boolean orderAsc) {

        if (nameCategory.isBlank()) {
            List<CategoryModel> categoryModels = categoryPersistencePort.getAllCategories();

            if (orderAsc) {
                categoryModels.sort(Comparator.comparing(CategoryModel::getName));
            } else {
                categoryModels.sort(Comparator.comparing(CategoryModel::getName).reversed());
            }

            int totalElements = categoryModels.size();
            int fromIndex = (page-1)*size;
            int toIndex = Math.min(fromIndex + size, totalElements);

            if (fromIndex >= totalElements || fromIndex < 0) {
                throw new PageNotFoundException();
            }

            List<CategoryModel> pageContent = categoryModels.subList(fromIndex, toIndex);

            return new Pagination<>(pageContent, page, size, totalElements);
        }
        Optional<CategoryModel> categoryFound = categoryPersistencePort.findByName(nameCategory);

        List<CategoryModel> categoryFoundList = List.of(categoryFound.orElseThrow(CategoryNotFoundException::new));

        if (page != 1) {
            throw new PageNotFoundException();
        }

        return new Pagination<>(categoryFoundList, page, size, categoryFoundList.size());
    }
}
