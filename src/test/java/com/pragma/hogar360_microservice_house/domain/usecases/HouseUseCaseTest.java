package com.pragma.hogar360_microservice_house.domain.usecases;

import com.pragma.hogar360_microservice_house.domain.exceptions.*;
import com.pragma.hogar360_microservice_house.domain.model.*;
import com.pragma.hogar360_microservice_house.domain.model.filters.HouseFilterModel;
import com.pragma.hogar360_microservice_house.domain.ports.out.*;
import com.pragma.hogar360_microservice_house.domain.util.constants.StateHousesConstants;
import com.pragma.hogar360_microservice_house.domain.util.pagination.Pagination;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

import static com.pragma.hogar360_microservice_house.domain.util.constants.GlobalConstants.UTILITY_CLASS_MESSAGE;
import static com.pragma.hogar360_microservice_house.domain.util.pagination.PaginationConstants.*;
import static com.pragma.hogar360_microservice_house.utils.constants.HouseTestConstants.*;
import static com.pragma.hogar360_microservice_house.utils.testdata.TestDataHouse.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HouseUseCaseTest {

    @Mock
    private IHousePersistencePort housePersistencePort;
    @Mock
    private ICategoryPersistencePort categoryPersistencePort;
    @Mock
    private ILocationPersistencePort locationPersistencePort;
    @Mock
    private ISecurityServicePort securityServicePort;

    @InjectMocks
    private HouseUseCase houseUseCase;

    @Nested
    class PublishHouseTests {
        @Test
        void publish_WithValidData_ShouldSaveHouse() {
            HouseModel house = getValidPublishedHouse();
            when(locationPersistencePort.findByNeighborhoodAndCityNameAndDepartmentName(any(), any(), any()))
                    .thenReturn(Optional.of(getValidLocation()));
            when(categoryPersistencePort.findByName(any())).thenReturn(Optional.of(getValidCategory()));
            when(securityServicePort.getAuthenticatedEmail()).thenReturn(EMAIL_SELLER);

            houseUseCase.publish(house);

            verify(housePersistencePort).save(house);
            assertEquals(STATE_PUBLICATION_PAUSED, house.getPublicationStatus());
        }

        @Test
        void publish_WithFutureActiveDateBeyondLimit_ShouldThrowException() {
            HouseModel house = getHouseWithFutureActiveDate();

            assertThrows(HouseLimitActivePublicationDateExceedException.class,
                    () -> houseUseCase.publish(house));
        }

        @Test
        void publish_WithEmptyName_ShouldThrowException() {
            HouseModel house = getHouseWithEmptyName();

            assertThrows(HouseNameCannotBeEmptyException.class,
                    () -> houseUseCase.publish(house));
        }

        @Test
        void publish_WithInvalidLocation_ShouldThrowException() {
            HouseModel house = getValidPublishedHouse();
            when(locationPersistencePort.findByNeighborhoodAndCityNameAndDepartmentName(any(), any(), any()))
                    .thenReturn(Optional.empty());

            assertThrows(LocationNotFoundException.class,
                    () -> houseUseCase.publish(house));
        }

        @Test
        void publish_WithInvalidCategory_ShouldThrowException() {
            HouseModel house = getValidPublishedHouse();
            when(locationPersistencePort.findByNeighborhoodAndCityNameAndDepartmentName(any(), any(), any()))
                    .thenReturn(Optional.of(getValidLocation()));
            when(categoryPersistencePort.findByName(any())).thenReturn(Optional.empty());

            assertThrows(CategoryNotFoundException.class,
                    () -> houseUseCase.publish(house));
        }
    }

    @Nested
    class GetHousesTests {
        @Test
        void getHouses_WithValidFilters_AndOrderByPrice_ShouldReturnFilteredResults() {
            HouseFilterModel filter = getValidHouseFilter();
            List<HouseModel> houses = getMultipleHouses();
            when(housePersistencePort.findHousesByFilters(any(), any())).thenReturn(houses);

            Pagination<HouseModel> result = houseUseCase.getHouses(filter, null, DEFAULT_PAGE, DEFAULT_SIZE, PRICE_ORDER_BY_PAGINATION, ORDER_ASC_PAGINATION);

            assertFalse(result.getContent().isEmpty());
            verify(housePersistencePort).findHousesByFilters(filter, STATE_PUBLICATION_PUBLISHED);
        }

        @Test
        void getHouses_WithValidFilters_AndOrderByDepartment_ShouldReturnFilteredResults() {
            HouseFilterModel filter = getValidHouseFilter();
            List<HouseModel> houses = getMultipleHouses();
            when(housePersistencePort.findHousesByFilters(any(), any())).thenReturn(houses);

            Pagination<HouseModel> result = houseUseCase.getHouses(filter, null, DEFAULT_PAGE, DEFAULT_SIZE, DEPARTMENT_ORDER_BY_PAGINATION, ORDER_ASC_PAGINATION);

            assertFalse(result.getContent().isEmpty());
            verify(housePersistencePort).findHousesByFilters(filter, STATE_PUBLICATION_PUBLISHED);
        }

        @Test
        void getHouses_WithValidFilters_AndOrderByCity_ShouldReturnFilteredResults() {
            HouseFilterModel filter = getValidHouseFilter();
            List<HouseModel> houses = getMultipleHouses();
            when(housePersistencePort.findHousesByFilters(any(), any())).thenReturn(houses);

            Pagination<HouseModel> result = houseUseCase.getHouses(filter, null, DEFAULT_PAGE, DEFAULT_SIZE, CITY_ORDER_BY_PAGINATION, ORDER_ASC_PAGINATION);

            assertFalse(result.getContent().isEmpty());
            verify(housePersistencePort).findHousesByFilters(filter, STATE_PUBLICATION_PUBLISHED);
        }

        @Test
        void getHouses_WithValidFilters_AndOrderByBathroom_ShouldReturnFilteredResults() {
            HouseFilterModel filter = getValidHouseFilter();
            List<HouseModel> houses = getMultipleHouses();
            when(housePersistencePort.findHousesByFilters(any(), any())).thenReturn(houses);

            Pagination<HouseModel> result = houseUseCase.getHouses(filter, null, DEFAULT_PAGE, DEFAULT_SIZE, BATHROOM_ORDER_BY_PAGINATION, ORDER_ASC_PAGINATION);

            assertFalse(result.getContent().isEmpty());
            verify(housePersistencePort).findHousesByFilters(filter, STATE_PUBLICATION_PUBLISHED);
        }

        @Test
        void getHouses_WithValidFilters_AndOrderByBedroom_ShouldReturnFilteredResults() {
            HouseFilterModel filter = getValidHouseFilter();
            List<HouseModel> houses = getMultipleHouses();
            when(housePersistencePort.findHousesByFilters(any(), any())).thenReturn(houses);

            Pagination<HouseModel> result = houseUseCase.getHouses(filter, null, DEFAULT_PAGE, DEFAULT_SIZE, BEDROOM_ORDER_BY_PAGINATION, ORDER_ASC_PAGINATION);

            assertFalse(result.getContent().isEmpty());
            verify(housePersistencePort).findHousesByFilters(filter, STATE_PUBLICATION_PUBLISHED);
        }

        @Test
        void getHouses_WithValidFilters_AndOrderByCategory_ShouldReturnFilteredResults() {
            HouseFilterModel filter = getValidHouseFilter();
            List<HouseModel> houses = getMultipleHouses();
            when(housePersistencePort.findHousesByFilters(any(), any())).thenReturn(houses);

            Pagination<HouseModel> result = houseUseCase.getHouses(filter, null, DEFAULT_PAGE, DEFAULT_SIZE, CATEGORY_ORDER_BY_PAGINATION, ORDER_ASC_PAGINATION);

            assertFalse(result.getContent().isEmpty());
            verify(housePersistencePort).findHousesByFilters(filter, STATE_PUBLICATION_PUBLISHED);
        }

        @Test
        void getHouses_WithPriceOrder_ShouldReturnSortedResults() {
            List<HouseModel> houses = getMultipleHouses();
            when(housePersistencePort.findHousesByFilters(any(), any())).thenReturn(houses);

            Pagination<HouseModel> result = houseUseCase.getHouses(new HouseFilterModel(),
                    null, DEFAULT_PAGE, DEFAULT_SIZE, PRICE_ORDER_BY_PAGINATION, ORDER_ASC_PAGINATION);

            assertTrue(result.getContent().get(0).getPrice() <= result.getContent().get(1).getPrice());
        }

        @Test
        @DisplayName("Test StateHousesConstants Constructor ThrowsIllegalStateException")
        void testStateHousesConstantsConstructorThrowsIllegalStateException() {
            Exception exception = assertThrows(InvocationTargetException.class, () -> {
                Constructor<StateHousesConstants> constructor = StateHousesConstants.class.getDeclaredConstructor();
                constructor.setAccessible(true);
                constructor.newInstance();
            });

            Throwable cause = exception.getCause();
            assertNotNull(cause);
            assertEquals(IllegalStateException.class, cause.getClass());

            assertEquals(UTILITY_CLASS_MESSAGE, cause.getMessage());
        }

        @Test
        void getHouses_WithInvalidOrderBy_ShouldThrowException() {
            when(housePersistencePort.findHousesByFilters(any(), any())).thenReturn(getMultipleHouses());
            HouseFilterModel houseFilterModel = new HouseFilterModel();

            assertThrows(HouseOrderNotFoundException.class,
                    () -> houseUseCase.getHouses(houseFilterModel, null, DEFAULT_PAGE, DEFAULT_SIZE, ORDER_BY_OTHER_PAGINATION, ORDER_ASC_PAGINATION)
            );
        }

        @Test
        void getHouses_WithPagination_ShouldReturnPaginatedResults() {
            List<HouseModel> houses = getMultipleHouses();
            when(housePersistencePort.findHousesByFilters(any(), any())).thenReturn(houses);

            Pagination<HouseModel> result = houseUseCase.getHouses(new HouseFilterModel(),
                    null, DEFAULT_PAGE, SIZE_ONE, PRICE_ORDER_BY_PAGINATION, ORDER_ASC_PAGINATION);

            assertEquals(SIZE_ONE, result.getContent().size());
        }
    }
}