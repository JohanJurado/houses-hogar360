package com.pragma.hogar360_microservice_house.domain.usecases;

import com.pragma.hogar360_microservice_house.domain.exceptions.*;
import com.pragma.hogar360_microservice_house.domain.model.CategoryModel;
import com.pragma.hogar360_microservice_house.domain.ports.out.ICategoryPersistencePort;
import com.pragma.hogar360_microservice_house.domain.util.constants.DomainConstants;
import com.pragma.hogar360_microservice_house.domain.util.pagination.PaginationConstants;
import com.pragma.hogar360_microservice_house.domain.util.validations.Validations;
import com.pragma.hogar360_microservice_house.utils.TestConstants;
import com.pragma.hogar360_microservice_house.utils.TestDataCategory;
import com.pragma.hogar360_microservice_house.domain.util.pagination.Pagination;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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

        Mockito.when(categoryPersistencePort.findByName(categoryIn.getName().toUpperCase()))
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

        Mockito.when(categoryPersistencePort.findByName(categoryIn.getName().toUpperCase()))
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
        CategoryModel categoryIn = TestDataCategory.getCategoryMaxName();

        assertThrows(
                CategoryNameMaxSizeExceedException.class,
                () -> categoryUseCase.save(categoryIn),
                "Expected save to throw CategoryNameMaxSizeExceedException, but it didn't"
        );
        verify(categoryPersistencePort, never()).save(any(CategoryModel.class));
    }

    @Test
    @DisplayName("Show DescriptionMaxSizeException when the description exceed 90 characters")
    void checkWhenCategoryDescriptionMaxExceed(){
        CategoryModel categoryIn = TestDataCategory.getCategoryMaxDescription();

        assertThrows(
                CategoryDescriptionMaxSizeExceedException.class,
                () -> categoryUseCase.save(categoryIn),
                "Expected save to throw CategoryDescriptionMaxSizeExceedException, but it didn't"
        );
        verify(categoryPersistencePort, never()).save(any(CategoryModel.class));
    }

    @Test
    @DisplayName("Show CategoryNameCannotBeEmptyException when the name is null")
    void checkWhenCategoryNameIsNull(){
        CategoryModel categoryIn = TestDataCategory.getCategoryNameNull();

        assertThrows(
                CategoryNameCannotBeEmptyException.class,
                () -> categoryUseCase.save(categoryIn),
                "Expected save to throw CategoryNameCannotBeEmptyException, but it didn't"
        );
        verify(categoryPersistencePort, never()).save(any(CategoryModel.class));
    }

    @Test
    @DisplayName("Show CategoryDescriptionCannotBeEmptyException when the description is null")
    void checkWhenCategoryDescriptionIsNull(){
        CategoryModel categoryIn = TestDataCategory.getCategoryDescriptionNull();

        assertThrows(
                CategoryDescriptionCannotBeEmptyException.class,
                () -> categoryUseCase.save(categoryIn),
                "Expected save to throw CategoryDescriptionCannotBeEmptyException, but it didn't"
        );
        verify(categoryPersistencePort, never()).save(any(CategoryModel.class));
    }

    @Test
    @DisplayName("Show CategoryNameCannotBeEmptyException when the name is blank")
    void checkWhenCategoryNameIsBlank(){
        CategoryModel categoryIn = TestDataCategory.getCategoryNameBlank();

        assertThrows(
                CategoryNameCannotBeEmptyException.class,
                () -> categoryUseCase.save(categoryIn),
                "Expected save to throw CategoryNameCannotBeEmptyException, but it didn't"
        );
        verify(categoryPersistencePort, never()).save(any(CategoryModel.class));
    }

    @Test
    @DisplayName("Show CategoryDescriptionCannotBeEmptyException when the description is blank")
    void checkWhenCategoryDescriptionIsBlank(){
        CategoryModel categoryIn = TestDataCategory.getCategoryDescriptionBlank();

        assertThrows(
                CategoryDescriptionCannotBeEmptyException.class,
                () -> categoryUseCase.save(categoryIn),
                "Expected save to throw CategoryDescriptionCannotBeEmptyException, but it didn't"
        );
        verify(categoryPersistencePort, never()).save(any(CategoryModel.class));
    }

    @Test
    @DisplayName("Get categories when the name category is blank and order is asc")
    void checkWhenNameCategoryIsBlankAndOrderAscIsTrue(){
        String nameCategory = TestDataCategory.NAME_CATEGORY_BLANK_PAGINATION;
        Integer page = TestDataCategory.PAGE_PAGINATION;
        Integer size = TestDataCategory.SIZE_PAGINATION;
        boolean orderAsc = TestDataCategory.ORDER_ASC_PAGINATION;

        List<CategoryModel> categoryModelList = TestDataCategory.getCategoryModels();

        Mockito.when(categoryPersistencePort.getAllCategories())
                .thenReturn(categoryModelList);

        Pagination<CategoryModel> response = categoryUseCase.getCategories(nameCategory, page, size, orderAsc);

        assertEquals(categoryModelList.size(), response.getContent().size());
        assertEquals(TestDataCategory.PAGE_PAGINATION, response.getPageNumber());
        assertEquals(TestDataCategory.SIZE_PAGINATION, response.getPageSize());
        assertEquals(categoryModelList.size(), response.getTotalElements());

        verify(categoryPersistencePort, times(1)).getAllCategories();
    }

    @Test
    @DisplayName("Test Validation Constructor ThrowsIllegalStateException")
    void testValidationConstructorThrowsIllegalStateException() {
        Exception exception = assertThrows(InvocationTargetException.class, () -> {
            Constructor<Validations> constructor = Validations.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        });

        Throwable cause = exception.getCause();
        assertNotNull(cause, "La causa de la excepción no debe ser nula.");
        assertEquals(IllegalStateException.class, cause.getClass(), "La causa debe ser IllegalStateException.");

        assertEquals(DomainConstants.UTILITY_CLASS_MESSAGE, cause.getMessage(), "El mensaje de la excepción no coincide.");
    }

    @Test
    @DisplayName("Test PaginationConstants Constructor ThrowsIllegalStateException")
    void testPaginationConstantsConstructorThrowsIllegalStateException() {
        Exception exception = assertThrows(InvocationTargetException.class, () -> {
            Constructor<PaginationConstants> constructor = PaginationConstants.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        });

        Throwable cause = exception.getCause();
        assertNotNull(cause, "La causa de la excepción no debe ser nula.");
        assertEquals(IllegalStateException.class, cause.getClass(), "La causa debe ser IllegalStateException.");

        assertEquals(DomainConstants.UTILITY_CLASS_MESSAGE, cause.getMessage(), "El mensaje de la excepción no coincide.");
    }

    @Test
    @DisplayName("Test DomainConstants Constructor ThrowsIllegalStateException")
    void testDomainConstantsConstructorThrowsIllegalStateException() {
        Exception exception = assertThrows(InvocationTargetException.class, () -> {
            Constructor<DomainConstants> constructor = DomainConstants.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        });

        Throwable cause = exception.getCause();
        assertNotNull(cause, "La causa de la excepción no debe ser nula.");
        assertEquals(IllegalStateException.class, cause.getClass(), "La causa debe ser IllegalStateException.");

        assertEquals(DomainConstants.UTILITY_CLASS_MESSAGE, cause.getMessage(), "El mensaje de la excepción no coincide.");
    }

    @Test
    @DisplayName("Get categories when the name category is blank and order is desc")
    void checkWhenNameCategoryIsBlankAndOrderAscIsFalse(){
        String nameCategory = TestDataCategory.NAME_CATEGORY_BLANK_PAGINATION;
        Integer page = TestDataCategory.PAGE_PAGINATION;
        Integer size = TestDataCategory.SIZE_PAGINATION;
        boolean orderAsc = TestDataCategory.ORDER_DESC_PAGINATION;

        List<CategoryModel> categoryModelList = TestDataCategory.getCategoryModels();

        Mockito.when(categoryPersistencePort.getAllCategories())
                .thenReturn(categoryModelList);

        Pagination<CategoryModel> response = categoryUseCase.getCategories(nameCategory, page, size, orderAsc);

        assertEquals(categoryModelList.size(), response.getContent().size());
        assertEquals(TestDataCategory.PAGE_PAGINATION, response.getPageNumber());
        assertEquals(TestDataCategory.SIZE_PAGINATION, response.getPageSize());
        assertEquals(categoryModelList.size(), response.getTotalElements());

        verify(categoryPersistencePort, times(1)).getAllCategories();
    }

    @Test
    @DisplayName("Show PageNotFound when the page is not among the possible generated pages")
    void checkWhenNameCategoryIsBlankAndPageNotFound(){
        String nameCategory = TestDataCategory.NAME_CATEGORY_BLANK_PAGINATION;
        Integer page = TestDataCategory.PAGE_NOT_FOUND_PAGINATION;
        Integer size = TestDataCategory.SIZE_PAGINATION;
        boolean orderAsc = TestDataCategory.ORDER_ASC_PAGINATION;

        List<CategoryModel> categoryModelList = TestDataCategory.getCategoryModels();

        Mockito.when(categoryPersistencePort.getAllCategories())
                .thenReturn(categoryModelList);

        assertThrows(
                PageNotFoundException.class,
                () -> categoryUseCase.getCategories(nameCategory, page, size, orderAsc),
                "Expected show PageNotFoundException, but it didn't"
        );
        verify(categoryPersistencePort, never()).save(any(CategoryModel.class));
    }

    @Test
    @DisplayName("Get category when the name category is present")
    void checkWhenNameCategoryIsNotBlank(){
        String nameCategory = TestDataCategory.getNameCategory();
        Integer page = TestDataCategory.PAGE_PAGINATION;
        Integer size = TestDataCategory.SIZE_PAGINATION;
        boolean orderAsc = TestDataCategory.ORDER_DESC_PAGINATION;

        List<CategoryModel> categoryModelList = TestDataCategory.getCategoryModels();

        Mockito.when(categoryPersistencePort.findByName(nameCategory))
                .thenReturn(Optional.of(TestDataCategory.getCategory()));

        Pagination<CategoryModel> response = categoryUseCase.getCategories(nameCategory, page, size, orderAsc);

        assertEquals(categoryModelList.size(), response.getContent().size());
        assertEquals(TestDataCategory.PAGE_PAGINATION, response.getPageNumber());
        assertEquals(TestDataCategory.SIZE_PAGINATION, response.getPageSize());
        assertEquals(categoryModelList.size(), response.getTotalElements());

        verify(categoryPersistencePort, times(1)).findByName(nameCategory);

    }

    @Test
    @DisplayName("Show CategoryNotFoundException when the name category is present")
    void checkWhenNameCategoryIsNotBlankAndCategoryNotFound(){
        String nameCategory = TestDataCategory.getNameCategory();
        Integer page = TestDataCategory.PAGE_PAGINATION;
        Integer size = TestDataCategory.SIZE_PAGINATION;
        boolean orderAsc = TestDataCategory.ORDER_DESC_PAGINATION;

        Mockito.when(categoryPersistencePort.findByName(nameCategory))
                .thenReturn(Optional.empty());

        assertThrows(
                CategoryNotFoundException.class,
                () -> categoryUseCase.getCategories(nameCategory, page, size, orderAsc),
                "Expected show CategoryNotFoundException, but it didn't"
        );
        verify(categoryPersistencePort, never()).save(any(CategoryModel.class));

    }

    @Test
    @DisplayName("Show PageNotFound when the page is not among the possible generated pages and name category is present")
    void checkWhenNameCategoryIsNotBlankAndPageNotFound(){
        String nameCategory = TestDataCategory.getNameCategory();
        Integer page = TestDataCategory.PAGE_NOT_FOUND_PAGINATION;
        Integer size = TestDataCategory.SIZE_PAGINATION;
        boolean orderAsc = TestDataCategory.ORDER_DESC_PAGINATION;

        Mockito.when(categoryPersistencePort.findByName(nameCategory))
                .thenReturn(Optional.of(TestDataCategory.getCategory()));

        assertThrows(
                PageNotFoundException.class,
                () -> categoryUseCase.getCategories(nameCategory, page, size, orderAsc),
                "Expected show PageNotFound, but it didn't"
        );
        verify(categoryPersistencePort, never()).save(any(CategoryModel.class));

    }

}