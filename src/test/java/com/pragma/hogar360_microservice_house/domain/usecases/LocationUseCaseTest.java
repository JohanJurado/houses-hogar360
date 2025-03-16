package com.pragma.hogar360_microservice_house.domain.usecases;

import com.pragma.hogar360_microservice_house.domain.exceptions.*;
import com.pragma.hogar360_microservice_house.domain.model.CityModel;
import com.pragma.hogar360_microservice_house.domain.model.DepartmentModel;
import com.pragma.hogar360_microservice_house.domain.ports.out.ILocationPersistencePort;
import com.pragma.hogar360_microservice_house.domain.util.pagination.Pagination;
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

        Mockito.when(locationPersistencePort.findDepartmentByName(departmentIn.getName()))
                .thenReturn(Optional.empty());

        Mockito.when(locationPersistencePort.findCityByName(cityIn.getName()))
                .thenReturn(Optional.empty());

        locationUseCase.save(cityIn, departmentIn);

        verify(locationPersistencePort,
                times(1)).findDepartmentByName(departmentIn.getName());
        verify(locationPersistencePort,
                times(1)).saveDepartment(departmentIn);

        verify(locationPersistencePort,
                times(1)).findCityByName(cityIn.getName());
        verify(locationPersistencePort,
                times(1)).saveCity(cityIn);
    }

    @Test
    @DisplayName("Create location (only department)")
    void checkWhenOnlyDepartmentSavedCorrectly(){
        CityModel cityIn = TestDataLocation.getCityModel();
        DepartmentModel departmentIn = TestDataLocation.getDepartmentModel();

        Mockito.when(locationPersistencePort.findDepartmentByName(departmentIn.getName()))
                .thenReturn(Optional.empty());

        Mockito.when(locationPersistencePort.findCityByName(cityIn.getName()))
                .thenReturn(Optional.of(cityIn));

        locationUseCase.save(cityIn, departmentIn);

        verify(locationPersistencePort,
                times(1)).findDepartmentByName(departmentIn.getName());
        verify(locationPersistencePort,
                times(1)).saveDepartment(departmentIn);

        verify(locationPersistencePort,
                times(1)).findCityByName(cityIn.getName());
        verify(locationPersistencePort, never()).saveCity(any(CityModel.class));
    }

    @Test
    @DisplayName("Show LocationAlreadyExist when department already exist")
    void showLocationAlreadyExistWhenDepartmentAlreadyExist(){
        CityModel cityIn = TestDataLocation.getCityModel();
        DepartmentModel departmentIn = TestDataLocation.getDepartmentModel();

        Mockito.when(locationPersistencePort.findDepartmentByName(departmentIn.getName()))
                .thenReturn(Optional.of(departmentIn));

        assertThrows(
                LocationAlreadyExistsException.class,
                () -> locationUseCase.save(cityIn, departmentIn),
                "Expected save to throw LocationAlreadyExistsException, but it didn't"
        );

        verify(locationPersistencePort,
                times(1)).findDepartmentByName(departmentIn.getName());
        verify(locationPersistencePort, never()).saveDepartment(any(DepartmentModel.class));
        verify(locationPersistencePort, never()).findCityByName(any(String.class));
        verify(locationPersistencePort, never()).saveCity(any(CityModel.class));
    }

    @Test
    @DisplayName("Show LocationNameMaxSizeExceedException when the name of city exceed 50 characters")
    void showLocationNameMaxSizeExceedExceptionWhenCityNameExceed(){
        assertThrows(
                LocationNameMaxSizeExceedException.class,
                TestDataLocation::getCityModelMaxName,
                "Expected save to throw LocationNameMaxSizeException, but it didn't"
        );
        verify(locationPersistencePort, never()).saveCity(any(CityModel.class));
        verify(locationPersistencePort, never()).saveDepartment(any(DepartmentModel.class));
    }

    @Test
    @DisplayName("Show LocationNameMaxSizeExceedException when the name of department exceed 50 characters")
    void showLocationNameMaxSizeExceedExceptionWhenDepartmentNameExceed(){
        assertThrows(
                LocationNameMaxSizeExceedException.class,
                TestDataLocation::getDepartmentModelMaxName,
                "Expected save to throw LocationNameMaxSizeException, but it didn't"
        );
        verify(locationPersistencePort, never()).saveCity(any(CityModel.class));
        verify(locationPersistencePort, never()).saveDepartment(any(DepartmentModel.class));
    }

    @Test
    @DisplayName("Show LocationDescriptionMaxSizeExceedException when the description of city exceed 120 characters")
    void showLocationDescriptionMaxSizeExceedExceptionWhenCityDescriptionExceed(){
        assertThrows(
                LocationDescriptionMaxSizeExceedException.class,
                TestDataLocation::getCityModelMaxDescription,
                "Expected save to throw LocationDescriptionMaxSizeExceedException, but it didn't"
        );
        verify(locationPersistencePort, never()).saveCity(any(CityModel.class));
        verify(locationPersistencePort, never()).saveDepartment(any(DepartmentModel.class));
    }

    @Test
    @DisplayName("Show LocationDescriptionMaxSizeExceedException when the description of department exceed 120 characters")
    void showLocationDescriptionMaxSizeExceedExceptionWhenDepartmentDescriptionExceed(){
        assertThrows(
                LocationDescriptionMaxSizeExceedException.class,
                TestDataLocation::getDepartmentModelMaxDescription,
                "Expected save to throw LocationDescriptionMaxSizeExceedException, but it didn't"
        );
        verify(locationPersistencePort, never()).saveCity(any(CityModel.class));
        verify(locationPersistencePort, never()).saveDepartment(any(DepartmentModel.class));
    }

    @Test
    @DisplayName("show NullPointerException when name city is null")
    void showNullPointerExceptionWhenNameCityIsNull(){
        assertThrows(
                NullPointerException.class,
                TestDataLocation::getCityNameNull,
                "Expected save to throw NullPointerException, but it didn't"
        );
        verify(locationPersistencePort, never()).saveCity(any(CityModel.class));
        verify(locationPersistencePort, never()).saveDepartment(any(DepartmentModel.class));
    }

    @Test
    @DisplayName("show NullPointerException when name department is null")
    void showNullPointerExceptionWhenNameDepartmentIsNull(){
        assertThrows(
                NullPointerException.class,
                TestDataLocation::getDepartmentNameNull,
                "Expected save to throw NullPointerException, but it didn't"
        );
        verify(locationPersistencePort, never()).saveCity(any(CityModel.class));
        verify(locationPersistencePort, never()).saveDepartment(any(DepartmentModel.class));
    }

    @Test
    @DisplayName("show NullPointerException when name city is blank")
    void showNullPointerExceptionWhenNameCityIsBlank(){
        assertThrows(
                NullPointerException.class,
                TestDataLocation::getCityNameBlank,
                "Expected save to throw NullPointerException, but it didn't"
        );
        verify(locationPersistencePort, never()).saveCity(any(CityModel.class));
        verify(locationPersistencePort, never()).saveDepartment(any(DepartmentModel.class));
    }

    @Test
    @DisplayName("show NullPointerException when name department is blank")
    void showNullPointerExceptionWhenNameDepartmentIsBlank(){
        assertThrows(
                NullPointerException.class,
                TestDataLocation::getDepartmentNameBlank,
                "Expected save to throw NullPointerException, but it didn't"
        );
        verify(locationPersistencePort, never()).saveCity(any(CityModel.class));
        verify(locationPersistencePort, never()).saveDepartment(any(DepartmentModel.class));
    }

    @Test
    @DisplayName("show NullPointerException when description city is null")
    void showNullPointerExceptionWhenDescriptionCityIsNull(){
        assertThrows(
                NullPointerException.class,
                TestDataLocation::getCityDescriptionNull,
                "Expected save to throw NullPointerException, but it didn't"
        );
        verify(locationPersistencePort, never()).saveCity(any(CityModel.class));
        verify(locationPersistencePort, never()).saveDepartment(any(DepartmentModel.class));
    }

    @Test
    @DisplayName("show NullPointerException when description department is null")
    void showNullPointerExceptionWhenDescriptionDepartmentIsNull(){
        assertThrows(
                NullPointerException.class,
                TestDataLocation::getDepartmentDescriptionNull,
                "Expected save to throw NullPointerException, but it didn't"
        );
        verify(locationPersistencePort, never()).saveCity(any(CityModel.class));
        verify(locationPersistencePort, never()).saveDepartment(any(DepartmentModel.class));
    }

    @Test
    @DisplayName("show NullPointerException when description city is blank")
    void showNullPointerExceptionWhenDescriptionCityIsBlank(){
        assertThrows(
                NullPointerException.class,
                TestDataLocation::getCityDescriptionBlank,
                "Expected save to throw NullPointerException, but it didn't"
        );
        verify(locationPersistencePort, never()).saveCity(any(CityModel.class));
        verify(locationPersistencePort, never()).saveDepartment(any(DepartmentModel.class));
    }

    @Test
    @DisplayName("show NullPointerException when description department is blank")
    void showNullPointerExceptionWhenDescriptionDepartmentIsBlank(){
        assertThrows(
                NullPointerException.class,
                TestDataLocation::getDepartmentDescriptionBlank,
                "Expected save to throw NullPointerException, but it didn't"
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

        Mockito.when(locationPersistencePort.getAllDepartments())
                .thenReturn(TestDataLocation.getLocationsModels());

        Pagination<DepartmentModel> response = locationUseCase.getLocations(nameLocation, page, size, orderBy, orderAsc);

        assertEquals(TestDataLocation.getLocationsModels().size(), response.getContent().size());
        assertEquals(TestDataCategory.PAGE_PAGINATION, response.getPageNumber());
        assertEquals(TestDataCategory.SIZE_PAGINATION, response.getPageSize());

        verify(locationPersistencePort, times(1)).getAllDepartments();
    }

    @Test
    @DisplayName("get Locations Order By City Desc")
    void getLocationsOrderByCityDesc(){
        String nameLocation = TestDataLocation.NAME_LOCATION_BLANK_PAGINATION;
        Integer page = TestDataLocation.PAGE_PAGINATION;
        Integer size = TestDataLocation.SIZE_PAGINATION;
        String orderBy = TestDataLocation.ORDER_BY_CITY_PAGINATION;
        boolean orderAsc = TestDataLocation.ORDER_DESC_PAGINATION;

        Mockito.when(locationPersistencePort.getAllDepartments())
                .thenReturn(TestDataLocation.getLocationsModels());

        Pagination<DepartmentModel> response = locationUseCase.getLocations(nameLocation, page, size, orderBy, orderAsc);

        assertEquals(TestDataLocation.getLocationsModels().size(), response.getContent().size());
        assertEquals(TestDataCategory.PAGE_PAGINATION, response.getPageNumber());
        assertEquals(TestDataCategory.SIZE_PAGINATION, response.getPageSize());

        verify(locationPersistencePort, times(1)).getAllDepartments();
    }

    @Test
    @DisplayName("show PageNotFoundException When The Page Is Not Among The Possible Generated Pages")
    void showPageNotFoundException(){
        String nameLocation = TestDataLocation.NAME_LOCATION_BLANK_PAGINATION;
        Integer page = TestDataLocation.PAGE_NOT_FOUND_PAGINATION;
        Integer size = TestDataLocation.SIZE_PAGINATION;
        String orderBy = TestDataLocation.ORDER_BY_CITY_PAGINATION;
        boolean orderAsc = TestDataLocation.ORDER_ASC_PAGINATION;

        Mockito.when(locationPersistencePort.getAllDepartments())
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

        Mockito.when(locationPersistencePort.getAllDepartments())
                .thenReturn(TestDataLocation.getLocationsModelsSize1());

        Pagination<DepartmentModel> response = locationUseCase.getLocations(nameLocation, page, size, orderBy, orderAsc);

        assertEquals(TestDataLocation.getLocationsModelsSize1().size(), response.getContent().size());
        assertEquals(TestDataCategory.PAGE_PAGINATION, response.getPageNumber());
        assertEquals(TestDataCategory.SIZE_PAGINATION, response.getPageSize());

        verify(locationPersistencePort, times(1)).getAllDepartments();
    }

    @Test
    @DisplayName("order By Department Asc")
    void orderByDepartmentAsc(){
        String nameLocation = TestDataLocation.NAME_LOCATION_BLANK_PAGINATION;
        Integer page = TestDataLocation.PAGE_PAGINATION;
        Integer size = TestDataLocation.SIZE_PAGINATION;
        String orderBy = TestDataLocation.ORDER_BY_DEPARTMENT_PAGINATION;
        boolean orderAsc = TestDataLocation.ORDER_ASC_PAGINATION;

        Mockito.when(locationPersistencePort.getAllDepartments())
                .thenReturn(TestDataLocation.getLocationsModels());

        Pagination<DepartmentModel> response = locationUseCase.getLocations(nameLocation, page, size, orderBy, orderAsc);

        assertEquals(TestDataLocation.getLocationsModels().size(), response.getContent().size());
        assertEquals(TestDataCategory.PAGE_PAGINATION, response.getPageNumber());
        assertEquals(TestDataCategory.SIZE_PAGINATION, response.getPageSize());

        verify(locationPersistencePort, times(1)).getAllDepartments();
    }

    @Test
    @DisplayName("order By Department Desc")
    void orderByDepartmentDesc(){
        String nameLocation = TestDataLocation.NAME_LOCATION_BLANK_PAGINATION;
        Integer page = TestDataLocation.PAGE_PAGINATION;
        Integer size = TestDataLocation.SIZE_PAGINATION;
        String orderBy = TestDataLocation.ORDER_BY_DEPARTMENT_PAGINATION;
        boolean orderAsc = TestDataLocation.ORDER_DESC_PAGINATION;

        Mockito.when(locationPersistencePort.getAllDepartments())
                .thenReturn(TestDataLocation.getLocationsModels());

        Pagination<DepartmentModel> response = locationUseCase.getLocations(nameLocation, page, size, orderBy, orderAsc);

        assertEquals(TestDataLocation.getLocationsModels().size(), response.getContent().size());
        assertEquals(TestDataCategory.PAGE_PAGINATION, response.getPageNumber());
        assertEquals(TestDataCategory.SIZE_PAGINATION, response.getPageSize());

        verify(locationPersistencePort, times(1)).getAllDepartments();
    }

    @Test
    @DisplayName("show LocationOrderNotFoundException When Order By Other")
    void showLocationOrderNotFoundExceptionWhenOrderByOther(){
        String nameLocation = TestDataLocation.NAME_LOCATION_BLANK_PAGINATION;
        Integer page = TestDataLocation.PAGE_PAGINATION;
        Integer size = TestDataLocation.SIZE_PAGINATION;
        String orderBy = TestDataLocation.ORDER_BY_OTHER_PAGINATION;
        boolean orderAsc = TestDataLocation.ORDER_ASC_PAGINATION;

        Mockito.when(locationPersistencePort.getAllDepartments())
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

        Mockito.when(locationPersistencePort.findCityByName(nameLocation.toUpperCase()))
                .thenReturn(Optional.of(TestDataLocation.getCityModel()));

        Mockito.when(locationPersistencePort.findAllByCityName(nameLocation))
                .thenReturn(TestDataLocation.getLocationsModels());

        Pagination<DepartmentModel> response = locationUseCase.getLocations(nameLocation, page, size, orderBy, orderAsc);

        assertEquals(TestDataLocation.getLocationsModels().size(), response.getContent().size());
        assertEquals(TestDataCategory.PAGE_PAGINATION, response.getPageNumber());
        assertEquals(TestDataCategory.SIZE_PAGINATION, response.getPageSize());

        verify(locationPersistencePort, times(1)).findCityByName(nameLocation.toUpperCase());
        verify(locationPersistencePort, times(1)).findAllByCityName(nameLocation);
    }

    @Test
    @DisplayName("get Locations When Name Location Is A Department")
    void getLocationsWhenNameLocationIsADepartment(){
        String nameLocation = TestDataLocation.getNameLocation();
        Integer page = TestDataLocation.PAGE_PAGINATION;
        Integer size = TestDataLocation.SIZE_PAGINATION;
        String orderBy = TestDataLocation.ORDER_BY_CITY_PAGINATION;
        boolean orderAsc = TestDataLocation.ORDER_ASC_PAGINATION;

        Mockito.when(locationPersistencePort.findCityByName(nameLocation.toUpperCase()))
                .thenReturn(Optional.empty());

        Mockito.when(locationPersistencePort.findDepartmentByName(nameLocation.toUpperCase()))
                .thenReturn(Optional.of(TestDataLocation.getDepartmentModel()));

        Pagination<DepartmentModel> response = locationUseCase.getLocations(nameLocation, page, size, orderBy, orderAsc);

        assertEquals(List.of(TestDataLocation.getDepartmentModel()).size(), response.getContent().size());
        assertEquals(TestDataCategory.PAGE_PAGINATION, response.getPageNumber());
        assertEquals(TestDataCategory.SIZE_PAGINATION, response.getPageSize());

        verify(locationPersistencePort, times(1)).findCityByName(nameLocation.toUpperCase());
        verify(locationPersistencePort, times(2)).findDepartmentByName(nameLocation.toUpperCase());
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

        verify(locationPersistencePort, times(1)).findCityByName(nameLocation.toUpperCase());
        verify(locationPersistencePort, times(1)).findDepartmentByName(nameLocation.toUpperCase());
    }
}
