package com.pragma.hogar360_microservice_house.domain.usecases;

import com.pragma.hogar360_microservice_house.domain.exceptions.*;
import com.pragma.hogar360_microservice_house.domain.model.DepartmentModel;
import com.pragma.hogar360_microservice_house.domain.model.HouseModel;
import com.pragma.hogar360_microservice_house.domain.ports.out.ICategoryPersistencePort;
import com.pragma.hogar360_microservice_house.domain.ports.out.IHousePersistencePort;
import com.pragma.hogar360_microservice_house.domain.ports.out.ILocationPersistencePort;
import com.pragma.hogar360_microservice_house.domain.util.constants.DomainConstants;
import com.pragma.hogar360_microservice_house.domain.util.constants.StateHousesConstants;
import com.pragma.hogar360_microservice_house.domain.util.pagination.Pagination;
import com.pragma.hogar360_microservice_house.utils.TestConstants;
import com.pragma.hogar360_microservice_house.utils.TestDataCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.pragma.hogar360_microservice_house.utils.TestDataHouse.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
class HouseUseCaseTest {

    @Mock
    private IHousePersistencePort housePersistencePort;
    @Mock
    private ICategoryPersistencePort categoryPersistencePort;
    @Mock
    private ILocationPersistencePort locationPersistencePort;

    @InjectMocks
    private HouseUseCase houseUseCase;

    @Test
    @DisplayName("Publish Home Successfully Published State House")
    void publishHomeSuccessfullyPublishedStateHouse() {
        HouseModel house = getHouseWithPublishedState();
        when(locationPersistencePort.findCityByName(any())).thenReturn(getValidCityList());
        when(locationPersistencePort.findDepartmentByName(any())).thenReturn(Optional.of(getValidDepartment()));
        when(categoryPersistencePort.findByName(any())).thenReturn(Optional.of(getValidCategory()));

        houseUseCase.publish(house);

        verify(housePersistencePort).save(house);
        assertEquals(StateHousesConstants.PUBLISHED_STATE_HOUSE, house.getPublicationStatus());
    }

    @Test
    @DisplayName("Publish Home Successfully Paused State House")
    void publishHomeSuccessfullyPausedStateHouse() {
        HouseModel house = getHouseWithPausedState();
        when(locationPersistencePort.findCityByName(any())).thenReturn(getValidCityList());
        when(locationPersistencePort.findDepartmentByName(any())).thenReturn(Optional.of(getValidDepartment()));
        when(categoryPersistencePort.findByName(any())).thenReturn(Optional.of(getValidCategory()));

        houseUseCase.publish(house);

        verify(housePersistencePort).save(house);
        assertEquals(StateHousesConstants.PAUSED_STATE_HOUSE, house.getPublicationStatus());
    }

    @Test
    @DisplayName("Show LocationCityNotFound")
    void showLocationCityNotFound() {
        HouseModel house = getValidHouse();
        when(locationPersistencePort.findCityByName(any())).thenReturn(List.of());

        assertThrows(
                LocationCityNotFoundException.class,
                () -> houseUseCase.publish(house)
        );
    }

    @Test
    @DisplayName("Show LocationDepartmentNotFoundException")
    void showLocationDepartmentNotFoundException() {
        HouseModel house = getValidHouse();
        when(locationPersistencePort.findCityByName(any())).thenReturn(getValidCityList());
        when(locationPersistencePort.findDepartmentByName(any())).thenReturn(Optional.empty());

        assertThrows(
                LocationDepartmentNotFoundException.class,
                () -> houseUseCase.publish(house)
        );
    }

    @Test
    @DisplayName("Show LocationNotFoundException")
    void showLocationNotFoundException() {
        HouseModel house = getValidHouse();
        DepartmentModel wrongDepartment = getInvalidDepartment();

        when(locationPersistencePort.findCityByName(any())).thenReturn(getValidCityList());
        when(locationPersistencePort.findDepartmentByName(any())).thenReturn(Optional.of(wrongDepartment));

        assertThrows(
                LocationNotFoundException.class,
                () -> houseUseCase.publish(house)
        );
    }

    @Test
    @DisplayName("Show CategoryNotFoundException")
    void showCategoryNotFoundException() {
        HouseModel house = getValidHouse();
        when(locationPersistencePort.findCityByName(any())).thenReturn(getValidCityList());
        when(locationPersistencePort.findDepartmentByName(any())).thenReturn(Optional.of(getValidDepartment()));
        when(categoryPersistencePort.findByName(any())).thenReturn(Optional.empty());

        assertThrows(
                CategoryNotFoundException.class,
                () -> houseUseCase.publish(house)
        );
    }

    @Test
    @DisplayName("Show HouseNameCannotBeEmptyException")
    void showHouseNameCannotBeEmptyException() {
        HouseModel house = getHouseWithEmptyName();

        assertThrows(
                HouseNameCannotBeEmptyException.class,
                () -> houseUseCase.publish(house)
        );
    }

    @Test
    @DisplayName("Show HouseDescriptionCannotBeEmptyException")
    void showHouseDescriptionCannotBeEmptyException() {
        HouseModel house = getValidHouse();
        house.setDescription(EMPTY_STRING);

        assertThrows(
                HouseDescriptionCannotBeEmptyException.class,
                () -> houseUseCase.publish(house)
        );
    }

    @Test
    @DisplayName("Show HouseBedroomCountCannotBeEmptyException")
    void showHouseBedroomCountCannotBeEmptyException() {
        HouseModel house = getValidHouse();
        house.setBedroomCount((Long) NULL_VALUE);

        assertThrows(
                HouseBedroomCountCannotBeEmptyException.class,
                () -> houseUseCase.publish(house)
        );
    }

    @Test
    @DisplayName("Show HouseBathroomCountCannotBeEmptyException")
    void showHouseBathroomCountCannotBeEmptyException() {
        HouseModel house = getValidHouse();
        house.setBathroomCount((Long) NULL_VALUE);

        assertThrows(
                HouseBathroomCountCannotBeEmptyException.class,
                () -> houseUseCase.publish(house)
        );
    }

    @Test
    @DisplayName("Show HousePriceCannotBeEmptyException")
    void showHousePriceCannotBeEmptyException() {
        HouseModel house = getValidHouse();
        house.setPrice((Double) NULL_VALUE);

        assertThrows(
                HousePriceCannotBeEmptyException.class,
                () -> houseUseCase.publish(house)
        );
    }

    @Test
    @DisplayName("Show HouseActivePublicationDateCannotBeEmptyException")
    void showHouseActivePublicationDateCannotBeEmptyException() {
        HouseModel house = getValidHouse();
        house.setActivePublicationDate((LocalDate) NULL_VALUE);

        assertThrows(
                HouseActivePublicationDateCannotBeEmptyException.class,
                () -> houseUseCase.publish(house)
        );
    }

    @Test
    @DisplayName("Show HouseLocationCannotBeEmptyException When Name City Is Empty")
    void showHouseLocationCannotBeEmptyExceptionWhenNameCityIsEmpty() {
        HouseModel house = getHouseWithEmptyCityName();

        assertThrows(
                HouseLocationCannotBeEmptyException.class,
                () -> houseUseCase.publish(house)
        );
    }

    @Test
    @DisplayName("Show HouseLocationCannotBeEmptyException When Name Department Is Empty")
    void showHouseLocationCannotBeEmptyExceptionWhenNameDepartmentIsEmpty() {
        HouseModel house = getHouseWithEmptyDepartmentName();

        assertThrows(
                HouseLocationCannotBeEmptyException.class,
                () -> houseUseCase.publish(house)
        );
    }

    @Test
    @DisplayName("Show HouseCategoryCannotBeEmptyException")
    void showHouseCategoryCannotBeEmptyException() {
        HouseModel house = getHouseWithEmptyCategoryName();

        assertThrows(
                HouseCategoryCannotBeEmptyException.class,
                () -> houseUseCase.publish(house)
        );
    }

    @Test
    @DisplayName("Show HouseOrderNotFoundException When OrderBy Is Other")
    void showHouseOrderNotFoundExceptionWhenOrderByIsOther() {

        Mockito.when(housePersistencePort.findHousesByFilters(any(),any(), any(), any(), any(), any(),any()))
                .thenReturn(List.of());

        assertThrows(
                HouseOrderNotFoundException.class,
                () -> houseUseCase.getHouses((String) NULL_VALUE, (String) NULL_VALUE, (String) NULL_VALUE, (Long) NULL_VALUE,
                        (Long) NULL_VALUE, (Double) NULL_VALUE, (Double) NULL_VALUE, PAGE_PAGINATION,
                        SIZE_PAGINATION, ORDER_BY_OTHER_PAGINATION, ORDER_ASC_PAGINATION),
                "Expected show HouseOrderNotFoundException, but it didn't"
        );

        verify(housePersistencePort, times(TestConstants.VERIFY_ONE_INVOCATIONS)).findHousesByFilters(any(),any(), any(), any(), any(), any(),any());
    }

    @Test
    @DisplayName("Show HouseOrderNotFoundException When OrderBy Is City")
    void showHouseOrderNotFoundExceptionWhenOrderByIsCity() {

        Mockito.when(housePersistencePort.findHousesByFilters(any(),any(), any(), any(), any(), any(),any()))
                .thenReturn(List.of());

        Pagination<HouseModel> response = houseUseCase.getHouses((String) NULL_VALUE, (String) NULL_VALUE, (String) NULL_VALUE, (Long) NULL_VALUE,
                (Long) NULL_VALUE, (Double) NULL_VALUE, (Double) NULL_VALUE, PAGE_PAGINATION,
                SIZE_PAGINATION, CITY_ORDER_BY_PAGINATION, ORDER_ASC_PAGINATION);

        assertEquals(EMPTY_SIZE_LIST, response.getContent().size());
        assertEquals(TestDataCategory.PAGE_PAGINATION, response.getPageNumber());
        assertEquals(TestDataCategory.SIZE_PAGINATION, response.getPageSize());

        verify(housePersistencePort, times(TestConstants.VERIFY_ONE_INVOCATIONS)).findHousesByFilters(any(),any(), any(), any(), any(), any(),any());
    }

    @Test
    @DisplayName("Show HouseOrderNotFoundException When OrderBy Is Department")
    void showHouseOrderNotFoundExceptionWhenOrderByIsDepartment() {

        Mockito.when(housePersistencePort.findHousesByFilters(any(),any(), any(), any(), any(), any(),any()))
                .thenReturn(List.of());

        Pagination<HouseModel> response = houseUseCase.getHouses((String) NULL_VALUE, (String) NULL_VALUE, (String) NULL_VALUE, (Long) NULL_VALUE,
                (Long) NULL_VALUE, (Double) NULL_VALUE, (Double) NULL_VALUE, PAGE_PAGINATION,
                SIZE_PAGINATION, DEPARTMENT_ORDER_BY_PAGINATION, ORDER_ASC_PAGINATION);

        assertEquals(EMPTY_SIZE_LIST, response.getContent().size());
        assertEquals(TestDataCategory.PAGE_PAGINATION, response.getPageNumber());
        assertEquals(TestDataCategory.SIZE_PAGINATION, response.getPageSize());

        verify(housePersistencePort, times(TestConstants.VERIFY_ONE_INVOCATIONS)).findHousesByFilters(any(),any(), any(), any(), any(), any(),any());
    }

    @Test
    @DisplayName("Show HouseOrderNotFoundException When OrderBy Is Category")
    void showHouseOrderNotFoundExceptionWhenOrderByIsCategory() {

        Mockito.when(housePersistencePort.findHousesByFilters(any(),any(), any(), any(), any(), any(),any()))
                .thenReturn(List.of());

        Pagination<HouseModel> response = houseUseCase.getHouses((String) NULL_VALUE, (String) NULL_VALUE, (String) NULL_VALUE, (Long) NULL_VALUE,
                (Long) NULL_VALUE, (Double) NULL_VALUE, (Double) NULL_VALUE, PAGE_PAGINATION,
                SIZE_PAGINATION, CATEGORY_ORDER_BY_PAGINATION, ORDER_ASC_PAGINATION);

        assertEquals(EMPTY_SIZE_LIST, response.getContent().size());
        assertEquals(TestDataCategory.PAGE_PAGINATION, response.getPageNumber());
        assertEquals(TestDataCategory.SIZE_PAGINATION, response.getPageSize());

        verify(housePersistencePort, times(TestConstants.VERIFY_ONE_INVOCATIONS)).findHousesByFilters(any(),any(), any(), any(), any(), any(),any());
    }

    @Test
    @DisplayName("Show HouseOrderNotFoundException When OrderBy Is Bedroom")
    void showHouseOrderNotFoundExceptionWhenOrderByIsBedroom() {

        Mockito.when(housePersistencePort.findHousesByFilters(any(),any(), any(), any(), any(), any(),any()))
                .thenReturn(List.of());

        Pagination<HouseModel> response = houseUseCase.getHouses((String) NULL_VALUE, (String) NULL_VALUE, (String) NULL_VALUE, (Long) NULL_VALUE,
                (Long) NULL_VALUE, (Double) NULL_VALUE, (Double) NULL_VALUE, PAGE_PAGINATION,
                SIZE_PAGINATION, BEDROOM_ORDER_BY_PAGINATION, ORDER_ASC_PAGINATION);

        assertEquals(EMPTY_SIZE_LIST, response.getContent().size());
        assertEquals(TestDataCategory.PAGE_PAGINATION, response.getPageNumber());
        assertEquals(TestDataCategory.SIZE_PAGINATION, response.getPageSize());

        verify(housePersistencePort, times(TestConstants.VERIFY_ONE_INVOCATIONS)).findHousesByFilters(any(),any(), any(), any(), any(), any(),any());
    }

    @Test
    @DisplayName("Show HouseOrderNotFoundException When OrderBy Is Bathroom")
    void showHouseOrderNotFoundExceptionWhenOrderByIsBathroom() {

        Mockito.when(housePersistencePort.findHousesByFilters(any(),any(), any(), any(), any(), any(),any()))
                .thenReturn(List.of());

        Pagination<HouseModel> response = houseUseCase.getHouses((String) NULL_VALUE, (String) NULL_VALUE, (String) NULL_VALUE, (Long) NULL_VALUE,
                (Long) NULL_VALUE, (Double) NULL_VALUE, (Double) NULL_VALUE, PAGE_PAGINATION,
                SIZE_PAGINATION, BATHROOM_ORDER_BY_PAGINATION, ORDER_ASC_PAGINATION);

        assertEquals(EMPTY_SIZE_LIST, response.getContent().size());
        assertEquals(TestDataCategory.PAGE_PAGINATION, response.getPageNumber());
        assertEquals(TestDataCategory.SIZE_PAGINATION, response.getPageSize());

        verify(housePersistencePort, times(TestConstants.VERIFY_ONE_INVOCATIONS)).findHousesByFilters(any(),any(), any(), any(), any(), any(),any());
    }

    @Test
    @DisplayName("Show HouseOrderNotFoundException When OrderBy Is Price")
    void showHouseOrderNotFoundExceptionWhenOrderByIsPrice() {

        Mockito.when(housePersistencePort.findHousesByFilters(any(),any(), any(), any(), any(), any(),any()))
                .thenReturn(List.of());

        Pagination<HouseModel> response = houseUseCase.getHouses((String) NULL_VALUE, (String) NULL_VALUE, (String) NULL_VALUE, (Long) NULL_VALUE,
                (Long) NULL_VALUE, (Double) NULL_VALUE, (Double) NULL_VALUE, PAGE_PAGINATION,
                SIZE_PAGINATION, PRICE_ORDER_BY_PAGINATION, ORDER_ASC_PAGINATION);

        assertEquals(EMPTY_SIZE_LIST, response.getContent().size());
        assertEquals(TestDataCategory.PAGE_PAGINATION, response.getPageNumber());
        assertEquals(TestDataCategory.SIZE_PAGINATION, response.getPageSize());

        verify(housePersistencePort, times(TestConstants.VERIFY_ONE_INVOCATIONS)).findHousesByFilters(any(),any(), any(), any(), any(), any(),any());
    }

    @Test
    @DisplayName("Test StateHousesConstants Constructor ThrowsIllegalStateException")
    void testValidationConstructorThrowsIllegalStateException() {
        Exception exception = assertThrows(InvocationTargetException.class, () -> {
            Constructor<StateHousesConstants> constructor = StateHousesConstants.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        });

        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(IllegalStateException.class, cause.getClass());

        assertEquals(DomainConstants.UTILITY_CLASS_MESSAGE, cause.getMessage());
    }
}