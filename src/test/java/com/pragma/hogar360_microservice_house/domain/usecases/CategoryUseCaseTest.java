package com.pragma.hogar360_microservice_house.domain.usecases;

import com.pragma.hogar360_microservice_house.domain.exceptions.*;
import com.pragma.hogar360_microservice_house.domain.model.CategoryModel;
import com.pragma.hogar360_microservice_house.domain.ports.out.ICategoryPersistencePort;
import com.pragma.hogar360_microservice_house.domain.util.constants.DomainConstants;
import com.pragma.hogar360_microservice_house.domain.util.constants.GlobalConstants;
import com.pragma.hogar360_microservice_house.domain.util.pagination.PaginationConstants;
import com.pragma.hogar360_microservice_house.domain.util.pagination.Pagination;
import com.pragma.hogar360_microservice_house.domain.util.validations.CategoryValidation;
import com.pragma.hogar360_microservice_house.domain.util.validations.GlobalValidations;
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

import static com.pragma.hogar360_microservice_house.domain.util.constants.GlobalConstants.UTILITY_CLASS_MESSAGE;
import static com.pragma.hogar360_microservice_house.utils.constants.CategoryTestConstants.VALID_NAME_CATEGORY;
import static com.pragma.hogar360_microservice_house.utils.constants.GlobalTestConstants.*;
import static com.pragma.hogar360_microservice_house.utils.testdata.TestDataCategory.*;
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
        CategoryModel categoryIn = getCategory();

        Mockito.when(categoryPersistencePort.findByName(categoryIn.getName().toUpperCase()))
                    .thenReturn(Optional.empty());

        categoryUseCase.save(categoryIn);

        verify(categoryPersistencePort,
                times(VERIFY_ONE_INVOCATIONS)).findByName(categoryIn.getName());
        verify(categoryPersistencePort,
                times(VERIFY_ONE_INVOCATIONS)).save(categoryIn);
    }

    @Test
    @DisplayName("Show CategoryAlreadyExistException when the category exists in BD")
    void checkWhenCategoryAlreadyExists(){
        CategoryModel categoryIn = getCategory();

        Mockito.when(categoryPersistencePort.findByName(categoryIn.getName().toUpperCase()))
                .thenReturn(Optional.of(categoryIn));

        assertThrows(
                CategoryAlreadyExistsException.class,
                () -> categoryUseCase.save(categoryIn),
                "Expected save to throw CategoryAlreadyExistException, but it didn't"
        );

        verify(categoryPersistencePort,
                times(VERIFY_ONE_INVOCATIONS)).findByName(categoryIn.getName());
        verify(categoryPersistencePort, never()).save(any(CategoryModel.class));
    }

    @Test
    @DisplayName("Show NameMaxSizeException when the name exceed 50 characters")
    void checkWhenCategoryNameMaxExceed(){
        CategoryModel categoryIn = getCategoryMaxName();

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
        CategoryModel categoryIn = getCategoryMaxDescription();

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
        CategoryModel categoryIn = getCategoryNameNull();

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
        CategoryModel categoryIn = getCategoryDescriptionNull();

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
        CategoryModel categoryIn = getCategoryNameBlank();

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
        CategoryModel categoryIn = getCategoryDescriptionBlank();

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
        List<CategoryModel> categoryModelList = getCategoryModels();

        Mockito.when(categoryPersistencePort.findAllByName(NAME_BLANK_PAGINATION))
                .thenReturn(categoryModelList);

        Pagination<CategoryModel> response = categoryUseCase.getCategories(
                NAME_BLANK_PAGINATION, PAGE_PAGINATION, SIZE_PAGINATION, ORDER_ASC_PAGINATION
        );

        assertEquals(categoryModelList.size(), response.getContent().size());
        assertEquals(PAGE_PAGINATION, response.getPageNumber());
        assertEquals(SIZE_PAGINATION, response.getPageSize());
        assertEquals(categoryModelList.size(), response.getTotalElements());

        verify(categoryPersistencePort, times(VERIFY_ONE_INVOCATIONS)).findAllByName(NAME_BLANK_PAGINATION);
    }

    @Test
    @DisplayName("Test Validation Constructor ThrowsIllegalStateException")
    void testValidationConstructorThrowsIllegalStateException() {
        Exception exception = assertThrows(InvocationTargetException.class, () -> {
            Constructor<GlobalValidations> constructor = GlobalValidations.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        });

        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(IllegalStateException.class, cause.getClass());

        assertEquals(UTILITY_CLASS_MESSAGE, cause.getMessage());
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
        assertNotNull(cause);
        assertEquals(IllegalStateException.class, cause.getClass());

        assertEquals(UTILITY_CLASS_MESSAGE, cause.getMessage());
    }

    @Test
    @DisplayName("Test CategoryValidation Constructor ThrowsIllegalStateException")
    void testCategoryValidationConstructorThrowsIllegalStateException() {
        Exception exception = assertThrows(InvocationTargetException.class, () -> {
            Constructor<CategoryValidation> constructor = CategoryValidation.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        });

        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(IllegalStateException.class, cause.getClass());

        assertEquals(UTILITY_CLASS_MESSAGE, cause.getMessage());
    }

    @Test
    @DisplayName("Test GlobalConstants Constructor ThrowsIllegalStateException")
    void testGlobalConstantsConstructorThrowsIllegalStateException() {
        Exception exception = assertThrows(InvocationTargetException.class, () -> {
            Constructor<GlobalConstants> constructor = GlobalConstants.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        });

        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(IllegalStateException.class, cause.getClass());

        assertEquals(UTILITY_CLASS_MESSAGE, cause.getMessage());
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
        assertNotNull(cause);
        assertEquals(IllegalStateException.class, cause.getClass());

        assertEquals(UTILITY_CLASS_MESSAGE, cause.getMessage());
    }

    @Test
    @DisplayName("Get categories when the name category is blank and order is desc")
    void checkWhenNameCategoryIsBlankAndOrderAscIsFalse(){
        List<CategoryModel> categoryModelList = getCategoryModels();

        Mockito.when(categoryPersistencePort.findAllByName(NAME_BLANK_PAGINATION))
                .thenReturn(categoryModelList);

        Pagination<CategoryModel> response = categoryUseCase.getCategories(
                NAME_BLANK_PAGINATION, PAGE_PAGINATION, SIZE_PAGINATION, ORDER_DESC_PAGINATION
        );

        assertEquals(categoryModelList.size(), response.getContent().size());
        assertEquals(PAGE_PAGINATION, response.getPageNumber());
        assertEquals(SIZE_PAGINATION, response.getPageSize());
        assertEquals(categoryModelList.size(), response.getTotalElements());

        verify(categoryPersistencePort, times(VERIFY_ONE_INVOCATIONS)).findAllByName(NAME_BLANK_PAGINATION);
    }

    @Test
    @DisplayName("Show PageNotFound when the page is not among the possible generated pages")
    void checkWhenNameCategoryIsBlankAndPageNotFound(){
        List<CategoryModel> categoryModelList = getCategoryModels();

        Mockito.when(categoryPersistencePort.findAllByName(VALID_NAME_CATEGORY))
                .thenReturn(categoryModelList);

        assertThrows(
                PageNotFoundException.class,
                () -> categoryUseCase.getCategories(
                        VALID_NAME_CATEGORY, PAGE_NOT_FOUND_PAGINATION, SIZE_PAGINATION, ORDER_DESC_PAGINATION
                ),
                "Expected show PageNotFoundException, but it didn't"
        );
        verify(categoryPersistencePort, never()).save(any(CategoryModel.class));
    }

    @Test
    @DisplayName("Show PageNotFound when the page is negative")
    void checkWhenNameCategoryIsBlankAndPageIsNegative(){
        List<CategoryModel> categoryModelList = getCategoryModels();

        Mockito.when(categoryPersistencePort.findAllByName(VALID_NAME_CATEGORY))
                .thenReturn(categoryModelList);

        assertThrows(
                PageNotFoundException.class,
                () -> categoryUseCase.getCategories(
                        VALID_NAME_CATEGORY, PAGE_NEGATIVE_PAGINATION, SIZE_PAGINATION, ORDER_DESC_PAGINATION
                ),
                "Expected show PageNotFoundException, but it didn't"
        );
        verify(categoryPersistencePort, never()).save(any(CategoryModel.class));
    }

    @Test
    @DisplayName("Get category when the name category is present")
    void checkWhenNameCategoryIsNotBlank(){
        List<CategoryModel> categoryModelList = getCategoryModels();

        Mockito.when(categoryPersistencePort.findAllByName(VALID_NAME_CATEGORY))
                .thenReturn(getCategoryModels());

        Pagination<CategoryModel> response = categoryUseCase.getCategories(
                VALID_NAME_CATEGORY, PAGE_PAGINATION, SIZE_PAGINATION, ORDER_DESC_PAGINATION
        );

        assertEquals(categoryModelList.size(), response.getContent().size());
        assertEquals(PAGE_PAGINATION, response.getPageNumber());
        assertEquals(SIZE_PAGINATION, response.getPageSize());
        assertEquals(categoryModelList.size(), response.getTotalElements());

        verify(categoryPersistencePort, times(VERIFY_ONE_INVOCATIONS)).findAllByName(VALID_NAME_CATEGORY);
    }

    @Test
    @DisplayName("Show Empty List when the name category is present")
    void checkWhenNameCategoryIsNotBlankAndCategoryNotFound(){
        Mockito.when(categoryPersistencePort.findAllByName(VALID_NAME_CATEGORY))
                .thenReturn(List.of());

        Pagination<CategoryModel> response = categoryUseCase.getCategories(
                VALID_NAME_CATEGORY, PAGE_PAGINATION, SIZE_PAGINATION, ORDER_DESC_PAGINATION
        );

        assertEquals(SIZE_ZERO_PAGINATION, response.getContent().size());
        assertEquals(PAGE_PAGINATION, response.getPageNumber());
        assertEquals(SIZE_PAGINATION, response.getPageSize());
        assertEquals(SIZE_ZERO_PAGINATION, response.getTotalElements());

        verify(categoryPersistencePort, times(VERIFY_ONE_INVOCATIONS)).findAllByName(VALID_NAME_CATEGORY);
    }

    @Test
    @DisplayName("Show PageNotFound when the page is not among the possible generated pages and name category is present")
    void checkWhenNameCategoryIsNotBlankAndPageNotFound(){
        Mockito.when(categoryPersistencePort.findAllByName(VALID_NAME_CATEGORY))
                .thenReturn(getCategoryModels());

        assertThrows(
                PageNotFoundException.class,
                () -> categoryUseCase.getCategories(
                        VALID_NAME_CATEGORY, PAGE_NOT_FOUND_PAGINATION, SIZE_PAGINATION, ORDER_DESC_PAGINATION
                ),
                "Expected show PageNotFound, but it didn't"
        );
        verify(categoryPersistencePort, never()).save(any(CategoryModel.class));
    }
}