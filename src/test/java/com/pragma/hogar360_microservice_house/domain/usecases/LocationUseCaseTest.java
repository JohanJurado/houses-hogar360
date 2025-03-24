package com.pragma.hogar360_microservice_house.domain.usecases;

import com.pragma.hogar360_microservice_house.domain.exceptions.*;
import com.pragma.hogar360_microservice_house.domain.model.CityModel;
import com.pragma.hogar360_microservice_house.domain.model.DepartmentModel;
import com.pragma.hogar360_microservice_house.domain.ports.out.ILocationPersistencePort;
import com.pragma.hogar360_microservice_house.domain.util.pagination.Pagination;
import com.pragma.hogar360_microservice_house.utils.TestConstants;
import com.pragma.hogar360_microservice_house.utils.TestDataCategory;
import com.pragma.hogar360_microservice_house.utils.TestDataLocation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocationUseCaseTest {

    @Mock
    private ILocationPersistencePort locationPersistencePort;
    @InjectMocks
    private LocationUseCase locationUseCase;


    @Test
    @DisplayName("Create location (city and department)")
    void checkWhenCityAndDepartmentSavedCorrectly(){
        CityModel cityIn = TestDataLocation.getCityModel();
        DepartmentModel departmentIn = TestDataLocation.getDepartmentModel();

        Mockito.when(locationPersistencePort.findDepartmentByName(departmentIn.getName().toUpperCase()))
                .thenReturn(Optional.empty());

        Mockito.when(locationPersistencePort.findCityByName(cityIn.getName().toUpperCase()))
                .thenReturn(Optional.empty());

        locationUseCase.save(cityIn, departmentIn);

        verify(locationPersistencePort,
                times(TestConstants.VERIFY_ONE_INVOCATIONS)).findDepartmentByName(departmentIn.getName());
        verify(locationPersistencePort,
                times(TestConstants.VERIFY_ONE_INVOCATIONS)).saveDepartment(departmentIn);

        verify(locationPersistencePort,
                times(TestConstants.VERIFY_ONE_INVOCATIONS)).findCityByName(cityIn.getName());
        verify(locationPersistencePort,
                times(TestConstants.VERIFY_ONE_INVOCATIONS)).saveCity(cityIn);
    }

    @Test
    @DisplayName("Create location (only city)")
    void checkWhenOnlyDepartmentSavedCorrectly(){
        CityModel cityIn = TestDataLocation.getCityModel();
        DepartmentModel departmentIn = TestDataLocation.getDepartmentModel();

        Mockito.when(locationPersistencePort.findDepartmentByName(departmentIn.getName().toUpperCase()))
                .thenReturn(Optional.of(departmentIn));

        Mockito.when(locationPersistencePort.findCityByName(cityIn.getName().toUpperCase()))
                .thenReturn(Optional.empty());

        locationUseCase.save(cityIn, departmentIn);

        verify(locationPersistencePort,
                times(TestConstants.VERIFY_ONE_INVOCATIONS)).findDepartmentByName(departmentIn.getName());
        verify(locationPersistencePort,
                times(TestConstants.VERIFY_ONE_INVOCATIONS)).saveCity(cityIn);

        verify(locationPersistencePort,
                times(TestConstants.VERIFY_ONE_INVOCATIONS)).findCityByName(cityIn.getName());
        verify(locationPersistencePort, never()).saveDepartment(any(DepartmentModel.class));
    }

    @Test
    @DisplayName("Show LocationAlreadyExist when department already exist")
    void showLocationAlreadyExistWhenDepartmentAlreadyExist(){
        CityModel cityIn = TestDataLocation.getCityModel();
        DepartmentModel departmentIn = TestDataLocation.getDepartmentModel();

        Mockito.when(locationPersistencePort.findDepartmentByName(departmentIn.getName().toUpperCase()))
                .thenReturn(Optional.of(departmentIn));

        Mockito.when(locationPersistencePort.findCityByName(cityIn.getName().toUpperCase()))
                .thenReturn(Optional.of(List.of(cityIn)));

        assertThrows(
                LocationAlreadyExistsException.class,
                () -> locationUseCase.save(cityIn, departmentIn),
                "Expected save to throw LocationAlreadyExistsException, but it didn't"
        );

        verify(locationPersistencePort,
                times(TestConstants.VERIFY_ONE_INVOCATIONS)).findDepartmentByName(departmentIn.getName());
        verify(locationPersistencePort, never()).saveDepartment(any(DepartmentModel.class));
        verify(locationPersistencePort, times(TestConstants.VERIFY_ONE_INVOCATIONS)).findCityByName(cityIn.getName().toUpperCase());
        verify(locationPersistencePort, never()).saveCity(any(CityModel.class));
    }

    @Test
    @DisplayName("Show LocationNameMaxSizeExceedException when the name of city exceed 50 characters")
    void showLocationNameMaxSizeExceedExceptionWhenCityNameExceed(){
        CityModel cityIn = TestDataLocation.getCityModelMaxName();
        DepartmentModel departmentIn = TestDataLocation.getDepartmentModel();

        assertThrows(
                LocationNameMaxSizeExceedException.class,
                () -> locationUseCase.save(cityIn, departmentIn),
                "Expected save to throw LocationNameMaxSizeExceedException, but it didn't"
        );
        verify(locationPersistencePort, never()).saveCity(any(CityModel.class));
        verify(locationPersistencePort, never()).saveDepartment(any(DepartmentModel.class));
    }

    @Test
    @DisplayName("Show LocationNameMaxSizeExceedException when the name of department exceed 50 characters")
    void showLocationNameMaxSizeExceedExceptionWhenDepartmentNameExceed(){
        CityModel cityIn = TestDataLocation.getCityModel();
        DepartmentModel departmentIn = TestDataLocation.getDepartmentModelMaxName();

        assertThrows(
                LocationNameMaxSizeExceedException.class,
                () -> locationUseCase.save(cityIn, departmentIn),
                "Expected save to throw LocationNameMaxSizeExceedException, but it didn't"
        );
        verify(locationPersistencePort, never()).saveCity(any(CityModel.class));
        verify(locationPersistencePort, never()).saveDepartment(any(DepartmentModel.class));
    }

    @Test
    @DisplayName("Show LocationDescriptionMaxSizeExceedException when the description of city exceed 120 characters")
    void showLocationDescriptionMaxSizeExceedExceptionWhenCityDescriptionExceed(){
        CityModel cityIn = TestDataLocation.getCityModelMaxDescription();
        DepartmentModel departmentIn = TestDataLocation.getDepartmentModel();

        assertThrows(
                LocationDescriptionMaxSizeExceedException.class,
                () -> locationUseCase.save(cityIn, departmentIn),
                "Expected save to throw LocationDescriptionMaxSizeExceedException, but it didn't"
        );

        verify(locationPersistencePort, never()).saveCity(any(CityModel.class));
        verify(locationPersistencePort, never()).saveDepartment(any(DepartmentModel.class));
    }

    @Test
    @DisplayName("Show LocationDescriptionMaxSizeExceedException when the description of department exceed 120 characters")
    void showLocationDescriptionMaxSizeExceedExceptionWhenDepartmentDescriptionExceed(){
        CityModel cityIn = TestDataLocation.getCityModel();
        DepartmentModel departmentIn = TestDataLocation.getDepartmentModelMaxDescription();

        assertThrows(
                LocationDescriptionMaxSizeExceedException.class,
                () -> locationUseCase.save(cityIn, departmentIn),
                "Expected save to throw LocationDescriptionMaxSizeExceedException, but it didn't"
        );
        verify(locationPersistencePort, never()).saveCity(any(CityModel.class));
        verify(locationPersistencePort, never()).saveDepartment(any(DepartmentModel.class));
    }

    @Test
    @DisplayName("show LocationCityNameCannotBeEmptyException when name city is null")
    void showLocationCityNameCannotBeEmptyExceptionWhenNameCityIsNull(){
        CityModel cityIn = TestDataLocation.getCityNameNull();
        DepartmentModel departmentIn = TestDataLocation.getDepartmentModel();

        assertThrows(
                LocationCityNameCannotBeEmptyException.class,
                () -> locationUseCase.save(cityIn, departmentIn),
                "Expected save to throw LocationCityNameCannotBeEmptyException, but it didn't"
        );
        verify(locationPersistencePort, never()).saveCity(any(CityModel.class));
        verify(locationPersistencePort, never()).saveDepartment(any(DepartmentModel.class));
    }

    @Test
    @DisplayName("show LocationDepartmentNameCannotBeEmptyException when name department is null")
    void showLocationDepartmentNameCannotBeEmptyExceptionWhenNameDepartmentIsNull(){
        CityModel cityIn = TestDataLocation.getCityModel();
        DepartmentModel departmentIn = TestDataLocation.getDepartmentNameNull();

        assertThrows(
                LocationDepartmentNameCannotBeEmptyException.class,
                () -> locationUseCase.save(cityIn, departmentIn),
                "Expected save to throw LocationDepartmentNameCannotBeEmptyException, but it didn't"
        );
        verify(locationPersistencePort, never()).saveCity(any(CityModel.class));
        verify(locationPersistencePort, never()).saveDepartment(any(DepartmentModel.class));
    }

    @Test
    @DisplayName("show LocationCityNameCannotBeEmptyException when name city is blank")
    void showLocationCityNameCannotBeEmptyExceptionWhenNameCityIsBlank(){
        CityModel cityIn = TestDataLocation.getCityNameBlank();
        DepartmentModel departmentIn = TestDataLocation.getDepartmentModel();

        assertThrows(
                LocationCityNameCannotBeEmptyException.class,
                () -> locationUseCase.save(cityIn, departmentIn),
                "Expected save to throw LocationCityNameCannotBeEmptyException, but it didn't"
        );
        verify(locationPersistencePort, never()).saveCity(any(CityModel.class));
        verify(locationPersistencePort, never()).saveDepartment(any(DepartmentModel.class));
    }

    @Test
    @DisplayName("show LocationDepartmentNameCannotBeEmptyException when name department is blank")
    void showLocationDepartmentNameCannotBeEmptyExceptionWhenNameDepartmentIsBlank(){
        CityModel cityIn = TestDataLocation.getCityModel();
        DepartmentModel departmentIn = TestDataLocation.getDepartmentNameBlank();

        assertThrows(
                LocationDepartmentNameCannotBeEmptyException.class,
                () -> locationUseCase.save(cityIn, departmentIn),
                "Expected save to throw LocationCityNameCannotBeEmptyException, but it didn't"
        );
        verify(locationPersistencePort, never()).saveCity(any(CityModel.class));
        verify(locationPersistencePort, never()).saveDepartment(any(DepartmentModel.class));
    }

    @Test
    @DisplayName("show LocationCityDescriptionCannotBeEmptyException when description city is null")
    void showLocationCityDescriptionCannotBeEmptyExceptionWhenDescriptionCityIsNull(){
        CityModel cityIn = TestDataLocation.getCityDescriptionNull();
        DepartmentModel departmentIn = TestDataLocation.getDepartmentModel();

        assertThrows(
                LocationCityDescriptionCannotBeEmptyException.class,
                () -> locationUseCase.save(cityIn, departmentIn),
                "Expected save to throw LocationCityDescriptionCannotBeEmptyException, but it didn't"
        );
        verify(locationPersistencePort, never()).saveCity(any(CityModel.class));
        verify(locationPersistencePort, never()).saveDepartment(any(DepartmentModel.class));
    }

    @Test
    @DisplayName("show LocationDepartmentDescriptionCannotBeEmptyException when description department is null")
    void showLocationDepartmentDescriptionCannotBeEmptyExceptionWhenDescriptionDepartmentIsNull(){
        CityModel cityIn = TestDataLocation.getCityModel();
        DepartmentModel departmentIn = TestDataLocation.getDepartmentDescriptionNull();

        assertThrows(
                LocationDepartmentDescriptionCannotBeEmptyException.class,
                () -> locationUseCase.save(cityIn, departmentIn),
                "Expected save to throw LocationDepartmentDescriptionCannotBeEmptyException, but it didn't"
        );
        verify(locationPersistencePort, never()).saveCity(any(CityModel.class));
        verify(locationPersistencePort, never()).saveDepartment(any(DepartmentModel.class));
    }

    @Test
    @DisplayName("show LocationCityDescriptionCannotBeEmptyException when description city is blank")
    void showLocationCityDescriptionCannotBeEmptyExceptionWhenDescriptionCityIsBlank(){
        CityModel cityIn = TestDataLocation.getCityDescriptionBlank();
        DepartmentModel departmentIn = TestDataLocation.getDepartmentModel();

        assertThrows(
                LocationCityDescriptionCannotBeEmptyException.class,
                () -> locationUseCase.save(cityIn, departmentIn),
                "Expected save to throw LocationCityDescriptionCannotBeEmptyException, but it didn't"
        );
        verify(locationPersistencePort, never()).saveCity(any(CityModel.class));
        verify(locationPersistencePort, never()).saveDepartment(any(DepartmentModel.class));
    }

    @Test
    @DisplayName("show LocationDepartmentDescriptionCannotBeEmptyException when description department is blank")
    void showNullPointerExceptionWhenDescriptionDepartmentIsBlank(){
        CityModel cityIn = TestDataLocation.getCityModel();
        DepartmentModel departmentIn = TestDataLocation.getDepartmentDescriptionBlank();

        assertThrows(
                LocationDepartmentDescriptionCannotBeEmptyException.class,
                () -> locationUseCase.save(cityIn, departmentIn),
                "Expected save to throw LocationDepartmentDescriptionCannotBeEmptyException, but it didn't"
        );
        verify(locationPersistencePort, never()).saveCity(any(CityModel.class));
        verify(locationPersistencePort, never()).saveDepartment(any(DepartmentModel.class));
    }


    @Test
    @DisplayName("get Locations Order By City Asc")
    void getLocationsOrderByCityAsc(){
        String nameLocation = TestDataLocation.NAME_LOCATION_BLANK_PAGINATION;
        Integer page = TestDataLocation.PAGE_PAGINATION;
        Integer size = TestDataLocation.SIZE_PAGINATION;
        String orderBy = TestDataLocation.ORDER_BY_CITY_PAGINATION;
        boolean orderAsc = TestDataLocation.ORDER_ASC_PAGINATION;

        Mockito.when(locationPersistencePort.getAllCities())
                .thenReturn(TestDataLocation.getLocationsModels());

        Pagination<CityModel> response = locationUseCase.getLocations(nameLocation, page, size, orderBy, orderAsc);

        assertEquals(TestDataLocation.getLocationsModels().size(), response.getContent().size());
        assertEquals(TestDataCategory.PAGE_PAGINATION, response.getPageNumber());
        assertEquals(TestDataCategory.SIZE_PAGINATION, response.getPageSize());

        verify(locationPersistencePort, times(TestConstants.VERIFY_ONE_INVOCATIONS)).getAllCities();
    }

    @Test
    @DisplayName("get Locations Order By City Desc")
    void getLocationsOrderByCityDesc(){
        String nameLocation = TestDataLocation.NAME_LOCATION_BLANK_PAGINATION;
        Integer page = TestDataLocation.PAGE_PAGINATION;
        Integer size = TestDataLocation.SIZE_PAGINATION;
        String orderBy = TestDataLocation.ORDER_BY_CITY_PAGINATION;
        boolean orderAsc = TestDataLocation.ORDER_DESC_PAGINATION;

        Mockito.when(locationPersistencePort.getAllCities())
                .thenReturn(TestDataLocation.getLocationsModels());

        Pagination<CityModel> response = locationUseCase.getLocations(nameLocation, page, size, orderBy, orderAsc);

        assertEquals(TestDataLocation.getLocationsModels().size(), response.getContent().size());
        assertEquals(TestDataCategory.PAGE_PAGINATION, response.getPageNumber());
        assertEquals(TestDataCategory.SIZE_PAGINATION, response.getPageSize());

        verify(locationPersistencePort, times(TestConstants.VERIFY_ONE_INVOCATIONS)).getAllCities();
    }

    @Test
    @DisplayName("show PageNotFoundException When The Page Is Not Among The Possible Generated Pages")
    void showPageNotFoundException(){
        String nameLocation = TestDataLocation.NAME_LOCATION_BLANK_PAGINATION;
        Integer page = TestDataLocation.PAGE_NOT_FOUND_PAGINATION;
        Integer size = TestDataLocation.SIZE_PAGINATION;
        String orderBy = TestDataLocation.ORDER_BY_CITY_PAGINATION;
        boolean orderAsc = TestDataLocation.ORDER_ASC_PAGINATION;

        Mockito.when(locationPersistencePort.getAllCities())
                .thenReturn(TestDataLocation.getLocationsModels());

        assertThrows(
                PageNotFoundException.class,
                () -> locationUseCase.getLocations(nameLocation, page, size, orderBy, orderAsc),
                "Expected show PageNotFoundException, but it didn't"
        );
    }

    @Test
    @DisplayName("order By Department When List Size Is 1")
    void orderByDepartmentWhenListSizeIs1(){
        String nameLocation = TestDataLocation.NAME_LOCATION_BLANK_PAGINATION;
        Integer page = TestDataLocation.PAGE_PAGINATION;
        Integer size = TestDataLocation.SIZE_PAGINATION;
        String orderBy = TestDataLocation.ORDER_BY_CITY_PAGINATION;
        boolean orderAsc = TestDataLocation.ORDER_ASC_PAGINATION;

        Mockito.when(locationPersistencePort.getAllCities())
                .thenReturn(TestDataLocation.getLocationsModelsSize1());

        Pagination<CityModel> response = locationUseCase.getLocations(nameLocation, page, size, orderBy, orderAsc);

        assertEquals(TestDataLocation.getLocationsModelsSize1().size(), response.getContent().size());
        assertEquals(TestDataCategory.PAGE_PAGINATION, response.getPageNumber());
        assertEquals(TestDataCategory.SIZE_PAGINATION, response.getPageSize());

        verify(locationPersistencePort, times(TestConstants.VERIFY_ONE_INVOCATIONS)).getAllCities();
    }

    @Test
    @DisplayName("order By Department Asc")
    void orderByDepartmentAsc(){
        String nameLocation = TestDataLocation.NAME_LOCATION_BLANK_PAGINATION;
        Integer page = TestDataLocation.PAGE_PAGINATION;
        Integer size = TestDataLocation.SIZE_PAGINATION;
        String orderBy = TestDataLocation.ORDER_BY_DEPARTMENT_PAGINATION;
        boolean orderAsc = TestDataLocation.ORDER_ASC_PAGINATION;

        Mockito.when(locationPersistencePort.getAllCities())
                .thenReturn(TestDataLocation.getLocationsModels());

        Pagination<CityModel> response = locationUseCase.getLocations(nameLocation, page, size, orderBy, orderAsc);

        assertEquals(TestDataLocation.getLocationsModels().size(), response.getContent().size());
        assertEquals(TestDataCategory.PAGE_PAGINATION, response.getPageNumber());
        assertEquals(TestDataCategory.SIZE_PAGINATION, response.getPageSize());

        verify(locationPersistencePort, times(TestConstants.VERIFY_ONE_INVOCATIONS)).getAllCities();
    }

    @Test
    @DisplayName("order By Department Desc")
    void orderByDepartmentDesc(){
        String nameLocation = TestDataLocation.NAME_LOCATION_BLANK_PAGINATION;
        Integer page = TestDataLocation.PAGE_PAGINATION;
        Integer size = TestDataLocation.SIZE_PAGINATION;
        String orderBy = TestDataLocation.ORDER_BY_DEPARTMENT_PAGINATION;
        boolean orderAsc = TestDataLocation.ORDER_DESC_PAGINATION;

        Mockito.when(locationPersistencePort.getAllCities())
                .thenReturn(TestDataLocation.getLocationsModels());

        Pagination<CityModel> response = locationUseCase.getLocations(nameLocation, page, size, orderBy, orderAsc);

        assertEquals(TestDataLocation.getLocationsModels().size(), response.getContent().size());
        assertEquals(TestDataCategory.PAGE_PAGINATION, response.getPageNumber());
        assertEquals(TestDataCategory.SIZE_PAGINATION, response.getPageSize());

        verify(locationPersistencePort, times(TestConstants.VERIFY_ONE_INVOCATIONS)).getAllCities();
    }

    @Test
    @DisplayName("show LocationOrderNotFoundException When Order By Other")
    void showLocationOrderNotFoundExceptionWhenOrderByOther(){
        String nameLocation = TestDataLocation.NAME_LOCATION_BLANK_PAGINATION;
        Integer page = TestDataLocation.PAGE_PAGINATION;
        Integer size = TestDataLocation.SIZE_PAGINATION;
        String orderBy = TestDataLocation.ORDER_BY_OTHER_PAGINATION;
        boolean orderAsc = TestDataLocation.ORDER_ASC_PAGINATION;

        Mockito.when(locationPersistencePort.getAllCities())
                .thenReturn(TestDataLocation.getLocationsModels());

        assertThrows(
                LocationOrderNotFoundException.class,
                () -> locationUseCase.getLocations(nameLocation, page, size, orderBy, orderAsc),
                "Expected show LocationOrderNotFoundException, but it didn't"
        );
    }

    @Test
    @DisplayName("get Locations When Name Location Is A City")
    void getLocationsWhenNameLocationIsACity(){
        String nameLocation = TestDataLocation.getNameLocation();
        Integer page = TestDataLocation.PAGE_PAGINATION;
        Integer size = TestDataLocation.SIZE_PAGINATION;
        String orderBy = TestDataLocation.ORDER_BY_CITY_PAGINATION;
        boolean orderAsc = TestDataLocation.ORDER_ASC_PAGINATION;

        Mockito.when(locationPersistencePort.findDepartmentByName(nameLocation.toUpperCase()))
                .thenReturn(Optional.empty());

        Mockito.when(locationPersistencePort.findCityByName(nameLocation.toUpperCase()))
                .thenReturn(Optional.of(List.of(TestDataLocation.getCityModel())));

        Pagination<CityModel> response = locationUseCase.getLocations(nameLocation, page, size, orderBy, orderAsc);

        assertEquals(TestDataLocation.getCityModel().getName(), response.getContent().getFirst().getName());
        assertEquals(TestDataCategory.PAGE_PAGINATION, response.getPageNumber());
        assertEquals(TestDataCategory.SIZE_PAGINATION, response.getPageSize());

        verify(locationPersistencePort, times(TestConstants.VERIFY_ONE_INVOCATIONS)).findDepartmentByName(nameLocation.toUpperCase());
        verify(locationPersistencePort, times(TestConstants.VERIFY_ONE_INVOCATIONS)).findCityByName(nameLocation.toUpperCase());
    }

    @Test
    @DisplayName("get Locations When Name Location Is A Department")
    void getLocationsWhenNameLocationIsADepartment(){
        String nameLocation = TestDataLocation.getNameLocation();
        Integer page = TestDataLocation.PAGE_PAGINATION;
        Integer size = TestDataLocation.SIZE_PAGINATION;
        String orderBy = TestDataLocation.ORDER_BY_CITY_PAGINATION;
        boolean orderAsc = TestDataLocation.ORDER_ASC_PAGINATION;

        Mockito.when(locationPersistencePort.findDepartmentByName(nameLocation.toUpperCase()))
                .thenReturn(Optional.of(TestDataLocation.getDepartmentModel()));

        Mockito.when(locationPersistencePort.findAllByDepartmentName(nameLocation.toUpperCase()))
                .thenReturn(TestDataLocation.getLocationsModels());


        Pagination<CityModel> response = locationUseCase.getLocations(nameLocation, page, size, orderBy, orderAsc);

        assertEquals(TestDataLocation.getLocationsModels().size(), response.getContent().size());
        assertEquals(TestDataCategory.PAGE_PAGINATION, response.getPageNumber());
        assertEquals(TestDataCategory.SIZE_PAGINATION, response.getPageSize());

        verify(locationPersistencePort, times(TestConstants.VERIFY_ONE_INVOCATIONS)).findAllByDepartmentName(nameLocation.toUpperCase());
        verify(locationPersistencePort, times(TestConstants.VERIFY_ONE_INVOCATIONS)).findDepartmentByName(nameLocation.toUpperCase());
    }

    @Test
    @DisplayName("show LocationNotFoundException When Name Location Is Not A City Or Department")
    void showLocationNotFoundWhenNameLocationIsNotACityOrDepartment(){
        String nameLocation = TestDataLocation.getNameLocation();
        Integer page = TestDataLocation.PAGE_PAGINATION;
        Integer size = TestDataLocation.SIZE_PAGINATION;
        String orderBy = TestDataLocation.ORDER_BY_CITY_PAGINATION;
        boolean orderAsc = TestDataLocation.ORDER_ASC_PAGINATION;

        Mockito.when(locationPersistencePort.findCityByName(nameLocation.toUpperCase()))
                .thenReturn(Optional.empty());

        Mockito.when(locationPersistencePort.findDepartmentByName(nameLocation.toUpperCase()))
                .thenReturn(Optional.empty());

        assertThrows(
                LocationNotFoundException.class,
                () -> locationUseCase.getLocations(nameLocation, page, size, orderBy, orderAsc),
                "Expected show LocationNotFoundException, but it didn't"
        );

        verify(locationPersistencePort, times(TestConstants.VERIFY_ONE_INVOCATIONS)).findCityByName(nameLocation.toUpperCase());
        verify(locationPersistencePort, times(TestConstants.VERIFY_ONE_INVOCATIONS)).findDepartmentByName(nameLocation.toUpperCase());
    }
}
