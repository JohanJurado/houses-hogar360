package com.pragma.hogar360_microservice_house.domain.usecases;

import com.pragma.hogar360_microservice_house.domain.exceptions.*;
import com.pragma.hogar360_microservice_house.domain.model.*;
import com.pragma.hogar360_microservice_house.domain.ports.out.*;
import com.pragma.hogar360_microservice_house.domain.util.pagination.Pagination;
import com.pragma.hogar360_microservice_house.domain.util.validations.LocationValidation;
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
import static com.pragma.hogar360_microservice_house.domain.util.pagination.PaginationConstants.CITY_ORDER_BY_PAGINATION;
import static com.pragma.hogar360_microservice_house.domain.util.pagination.PaginationConstants.DEPARTMENT_ORDER_BY_PAGINATION;
import static com.pragma.hogar360_microservice_house.utils.constants.GlobalTestConstants.*;
import static com.pragma.hogar360_microservice_house.utils.constants.LocationTestConstants.*;
import static com.pragma.hogar360_microservice_house.utils.testdata.TestDataLocation.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocationUseCaseTest {

    @Mock
    private ILocationPersistencePort locationPersistencePort;
    @Mock
    private ICityPersistencePort cityPersistencePort;
    @Mock
    private IDepartmentPersistencePort departmentPersistencePort;

    @InjectMocks
    private LocationUseCase locationUseCase;

    @Nested
    class SaveLocationTests {
        @Test
        void save_WithValidData_ShouldSaveLocation() {
            LocationModel location = getValidLocation();
            when(departmentPersistencePort.findByName(anyString())).thenReturn(Optional.of(location.getCityModel().getDepartmentModel()));
            when(cityPersistencePort.findByNameAndDepartmentId(anyString(), anyLong())).thenReturn(Optional.of(location.getCityModel()));
            when(locationPersistencePort.findByNeighborhoodAndCityId(anyString(), anyLong())).thenReturn(Optional.empty());

            locationUseCase.save(location);

            verify(locationPersistencePort).save(location);
        }

        @Test
        void save_WithDuplicateDepartmentName_ShouldSaveLocation() {
            LocationModel location = getValidLocation();
            DepartmentModel departmentModel = getValidDepartment();
            CityModel cityModel = getValidCity();
            when(departmentPersistencePort.findByName(anyString())).thenReturn(Optional.empty());
            when(departmentPersistencePort.save(location.getCityModel().getDepartmentModel())).thenReturn(departmentModel);
            when(cityPersistencePort.findByNameAndDepartmentId(anyString(), anyLong())).thenReturn(Optional.of(cityModel));
            when(locationPersistencePort.findByNeighborhoodAndCityId(anyString(), anyLong())).thenReturn(Optional.empty());

            locationUseCase.save(location);

            verify(locationPersistencePort).save(location);
        }

        @Test
        void save_WithLongCityName_ShouldThrowException() {
            LocationModel location = getValidLocation();
            location.getCityModel().setName(INVALID_LONG_CITY_NAME);

            assertThrows(LocationNameMaxSizeExceedException.class, () -> locationUseCase.save(location));
        }

        @Test
        void save_WithEmptyCityDescription_ShouldThrowException() {
            LocationModel location = getValidLocation();
            location.getCityModel().setDescription(EMPTY_STRING);

            assertThrows(CityDescriptionCannotBeEmptyException.class, () -> locationUseCase.save(location));
        }

        @Test
        @DisplayName("Test LocationValidation Constructor ThrowsIllegalStateException")
        void testLocationValidationConstructorThrowsIllegalStateException() {
            Exception exception = assertThrows(InvocationTargetException.class, () -> {
                Constructor<LocationValidation> constructor = LocationValidation.class.getDeclaredConstructor();
                constructor.setAccessible(true);
                constructor.newInstance();
            });

            Throwable cause = exception.getCause();
            assertNotNull(cause);
            assertEquals(IllegalStateException.class, cause.getClass());

            assertEquals(UTILITY_CLASS_MESSAGE, cause.getMessage());
        }

        @Test
        void save_WithDuplicateNeighborhoodInSameCity_ShouldThrowException() {
            LocationModel location = getValidLocation();
            DepartmentModel departmentModel = getValidDepartment();
            CityModel cityModel = getValidCity();
            when(departmentPersistencePort.findByName(anyString())).thenReturn(Optional.of(departmentModel));
            when(cityPersistencePort.findByNameAndDepartmentId(anyString(), anyLong())).thenReturn(Optional.of(cityModel));
            when(locationPersistencePort.findByNeighborhoodAndCityId(anyString(), anyLong())).thenReturn(Optional.of(location));

            assertThrows(LocationAlreadyExistsException.class, () -> locationUseCase.save(location));
        }
    }

    @Nested
    class GetLocationsTests {
        @Test
        void getLocations_WithSearchTerm_ShouldReturnMatchingLocations() {
            List<LocationModel> locations = getMultipleLocations();
            when(locationPersistencePort.findAllByCityOrDepartment(anyString())).thenReturn(locations);

            Pagination<LocationModel> result = locationUseCase.getLocations(
                    SEARCH_TERM_PARTIAL, PAGE_PAGINATION, SIZE_PAGINATION, CITY_ORDER_BY_PAGINATION, ORDER_ASC_PAGINATION);

            assertEquals(locations.size(), result.getContent().size());
        }

        @Test
        void getLocations_WithCityOrderAsc_ShouldReturnOrderedResults() {
            List<LocationModel> locations = getMultipleLocations();
            when(locationPersistencePort.findAllByCityOrDepartment(anyString())).thenReturn(locations);

            Pagination<LocationModel> result = locationUseCase.getLocations(
                    NAME_BLANK_PAGINATION, PAGE_PAGINATION, SIZE_PAGINATION, CITY_ORDER_BY_PAGINATION, ORDER_ASC_PAGINATION);

            assertTrue(result.getContent().get(0).getCityModel().getName()
                    .compareTo(result.getContent().get(1).getCityModel().getName()) <= 0);
        }

        @Test
        void getLocations_WithDepartmentOrderDesc_ShouldReturnOrderedResults() {
            List<LocationModel> locations = getMultipleLocations();
            when(locationPersistencePort.findAllByCityOrDepartment(anyString())).thenReturn(locations);

            Pagination<LocationModel> result = locationUseCase.getLocations(
                    NAME_BLANK_PAGINATION, PAGE_PAGINATION, SIZE_PAGINATION, DEPARTMENT_ORDER_BY_PAGINATION, ORDER_DESC_PAGINATION);

            assertTrue(result.getContent().get(0).getCityModel().getDepartmentModel().getName()
                    .compareTo(result.getContent().get(1).getCityModel().getDepartmentModel().getName()) >= 0);
        }

        @Test
        void getLocations_WithInvalidOrderBy_ShouldThrowException() {
            List<LocationModel> locations = getMultipleLocations();
            when(locationPersistencePort.findAllByCityOrDepartment(anyString())).thenReturn(locations);

            assertThrows(LocationOrderNotFoundException.class,
                    () -> locationUseCase.getLocations(
                            NAME_BLANK_PAGINATION, PAGE_PAGINATION, SIZE_PAGINATION, OTHER_PAGINATION, ORDER_ASC_PAGINATION
                    )
            );
        }
    }
}