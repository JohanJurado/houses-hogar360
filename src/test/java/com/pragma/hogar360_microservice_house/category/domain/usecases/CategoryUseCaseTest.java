package com.pragma.hogar360_microservice_house.category.domain.usecases;

import com.pragma.hogar360_microservice_house.category.domain.exceptions.DescriptionMaxSizeException;
import com.pragma.hogar360_microservice_house.category.domain.exceptions.NameMaxSizeException;
import com.pragma.hogar360_microservice_house.category.domain.exceptions.CategoryAlreadyExistsException;
import com.pragma.hogar360_microservice_house.category.domain.model.CategoryModel;
import com.pragma.hogar360_microservice_house.category.domain.ports.out.ICategoryPersistencePort;
import com.pragma.hogar360_microservice_house.category.domain.util.constants.DomainConstants;
import com.pragma.hogar360_microservice_house.category.utils.TestDataCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryUseCaseTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;
    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @Test
    @DisplayName("Create Category")
    void checkWhenCategorySavedCorrectly(){
        CategoryModel categoryIn = TestDataCategory.getCategory();

        Mockito.when(categoryPersistencePort.findByName(categoryIn.getName()))
                    .thenReturn(Optional.empty());

        categoryUseCase.save(categoryIn);

        verify(categoryPersistencePort,
                times(1)).findByName(categoryIn.getName());
        verify(categoryPersistencePort,
                times(1)).save(categoryIn);
    }

    @Test
    @DisplayName("Show CategoryAlreadyExistException when the category exists in BD")
    void checkWhenCategoryAlreadyExists(){
        CategoryModel categoryIn = TestDataCategory.getCategory();

        Mockito.when(categoryPersistencePort.findByName(categoryIn.getName()))
                .thenReturn(Optional.of(categoryIn));

        assertThrows(
                CategoryAlreadyExistsException.class,
                () -> categoryUseCase.save(categoryIn),
                "Expected save to throw CategoryAlreadyExistException, but it didn't"
        );

        verify(categoryPersistencePort,
                times(1)).findByName(categoryIn.getName());
        verify(categoryPersistencePort, never()).save(any(CategoryModel.class));
    }

    @Test
    @DisplayName("Show NameMaxSizeException when the name exceed 50 characters")
    void checkWhenCategoryNameMaxExceed(){
        assertThrows(
                NameMaxSizeException.class,
                TestDataCategory::getCategoryMaxName,
                "Expected save to throw NameMaxSizeException, but it didn't"
        );
        verify(categoryPersistencePort, never()).save(any(CategoryModel.class));
    }

    @Test
    @DisplayName("Show DescriptionMaxSizeException when the description exceed 90 characters")
    void checkWhenCategoryDescriptionMaxExceed(){
        assertThrows(
                DescriptionMaxSizeException.class,
                TestDataCategory::getCategoryMaxDescription,
                "Expected save to throw DescriptionMaxSizeException, but it didn't"
        );
        verify(categoryPersistencePort, never()).save(any(CategoryModel.class));
    }

    @Test
    @DisplayName("Show NullPointerException when the name is null")
    void checkWhenCategoryNameIsNull(){
        Exception e = assertThrows(
                NullPointerException.class,
                TestDataCategory::getCategoryNameNull,
                "Expected save to throw NullPointerException, but it didn't"
        );
        assertEquals(DomainConstants.FIELD_NAME_NULL_MESSAGE, e.getMessage());
        verify(categoryPersistencePort, never()).save(any(CategoryModel.class));
    }

    @Test
    @DisplayName("Show NullPointerException when the description is null")
    void checkWhenCategoryDescriptionIsNull(){
        Exception e = assertThrows(
                NullPointerException.class,
                TestDataCategory::getCategoryDescriptionNull,
                "Expected save to throw NullPointerException, but it didn't"
        );
        assertEquals(DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE, e.getMessage());
        verify(categoryPersistencePort, never()).save(any(CategoryModel.class));
    }

    @Test
    @DisplayName("Show NullPointerException when the name is blank")
    void checkWhenCategoryNameIsBlank(){
        Exception e = assertThrows(
                NullPointerException.class,
                TestDataCategory::getCategoryNameBlank,
                "Expected save to throw NullPointerException, but it didn't"
        );
        assertEquals(DomainConstants.FIELD_NAME_NULL_MESSAGE, e.getMessage());
        verify(categoryPersistencePort, never()).save(any(CategoryModel.class));
    }

    @Test
    @DisplayName("Show NullPointerException when the description is blank")
    void checkWhenCategoryDescriptionIsBlank(){
        Exception e = assertThrows(
                NullPointerException.class,
                TestDataCategory::getCategoryDescriptionBlank,
                "Expected save to throw NullPointerException, but it didn't"
        );
        assertEquals(DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE, e.getMessage());
        verify(categoryPersistencePort, never()).save(any(CategoryModel.class));
    }

}