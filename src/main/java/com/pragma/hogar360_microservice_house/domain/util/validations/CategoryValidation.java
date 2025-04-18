package com.pragma.hogar360_microservice_house.domain.util.validations;

import com.pragma.hogar360_microservice_house.domain.exceptions.CategoryDescriptionCannotBeEmptyException;
import com.pragma.hogar360_microservice_house.domain.exceptions.CategoryDescriptionMaxSizeExceedException;
import com.pragma.hogar360_microservice_house.domain.exceptions.CategoryNameCannotBeEmptyException;
import com.pragma.hogar360_microservice_house.domain.exceptions.CategoryNameMaxSizeExceedException;
import com.pragma.hogar360_microservice_house.domain.model.CategoryModel;

import static com.pragma.hogar360_microservice_house.domain.util.constants.DomainConstants.MAX_DESCRIPTION_SIZE_CATEGORY;
import static com.pragma.hogar360_microservice_house.domain.util.constants.DomainConstants.MAX_NAME_SIZE_CATEGORY;
import static com.pragma.hogar360_microservice_house.domain.util.constants.GlobalConstants.UTILITY_CLASS_MESSAGE;
import static com.pragma.hogar360_microservice_house.domain.util.validations.GlobalValidations.*;

public class CategoryValidation {

    private CategoryValidation() {
        throw new IllegalStateException(UTILITY_CLASS_MESSAGE);
    }

    public static void validationByCategoryAttributes(CategoryModel categoryModel){
        validationByAttributeIsNullOrBlank(categoryModel.getName(), new CategoryNameCannotBeEmptyException());
        validationByAttributeIsNullOrBlank(categoryModel.getDescription(), new CategoryDescriptionCannotBeEmptyException());
        validationByLimitCharacters(categoryModel.getName(), MAX_NAME_SIZE_CATEGORY, new CategoryNameMaxSizeExceedException());
        validationByLimitCharacters(
                categoryModel.getDescription(), MAX_DESCRIPTION_SIZE_CATEGORY,
                new CategoryDescriptionMaxSizeExceedException()
        );
    }

    public static void toUpperStringCategoryAttributes(CategoryModel categoryModel){
        categoryModel.setName(normalizeToUpper(categoryModel.getName()));
        categoryModel.setDescription(normalizeToUpper(categoryModel.getDescription()));
    }
}
