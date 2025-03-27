package com.pragma.hogar360_microservice_house.domain.usecases;

import com.pragma.hogar360_microservice_house.domain.exceptions.*;
import com.pragma.hogar360_microservice_house.domain.model.LocationModel;
import com.pragma.hogar360_microservice_house.domain.ports.out.ICityPersistencePort;
import com.pragma.hogar360_microservice_house.domain.ports.out.IDepartmentPersistencePort;
import com.pragma.hogar360_microservice_house.domain.ports.out.ILocationPersistencePort;
import com.pragma.hogar360_microservice_house.domain.util.pagination.Pagination;
import com.pragma.hogar360_microservice_house.utils.TestDataLocation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
    @Mock
    private IDepartmentPersistencePort departmentPersistencePort;
    @Mock
    private ICityPersistencePort cityPersistencePort;
    @InjectMocks
    private LocationUseCase locationUseCase;

    @Test
    void save_WithValidLocation_ShouldSaveSuccessfully() {
        // Arrange
        LocationModel validLocation = TestDataLocation.getValidLocation();

        when(departmentPersistencePort.findByName(any())).thenReturn(Optional.empty());
        when(departmentPersistencePort.save(any())).thenReturn(TestDataLocation.getValidDepartment());
        when(cityPersistencePort.findByNameAndDepartmentId(any(), any())).thenReturn(Optional.empty());
        when(cityPersistencePort.save(any())).thenReturn(TestDataLocation.getValidCity());
        when(locationPersistencePort.findByNeighborhoodAndCityId(any(), any())).thenReturn(Optional.empty());

        // Act
        locationUseCase.save(validLocation);

        // Assert
        verify(locationPersistencePort).save(validLocation);
    }

    @Test
    void save_WithEmptyNeighborhood_ShouldThrowException() {
        // Arrange
        LocationModel invalidLocation = TestDataLocation.getLocationWithEmptyNeighborhood();

        // Act & Assert
        assertThrows(LocationNeighborhoodCannotBeEmptyException.class,
                () -> locationUseCase.save(invalidLocation));
    }

    @Test
    void save_WithLongCityName_ShouldThrowException() {
        // Arrange
        LocationModel invalidLocation = TestDataLocation.getLocationWithLongName();

        // Act & Assert
        assertThrows(LocationNameMaxSizeExceedException.class,
                () -> locationUseCase.save(invalidLocation));
    }

    @Test
    void save_WithExistingLocation_ShouldThrowException() {
        // Arrange
        LocationModel validLocation = TestDataLocation.getValidLocation();

        when(departmentPersistencePort.findByName(any())).thenReturn(Optional.of(TestDataLocation.getValidDepartment()));
        when(cityPersistencePort.findByNameAndDepartmentId(any(), any())).thenReturn(Optional.of(TestDataLocation.getValidCity()));
        when(locationPersistencePort.findByNeighborhoodAndCityId(any(), any())).thenReturn(Optional.of(validLocation));

        // Act & Assert
        assertThrows(LocationAlreadyExistsException.class,
                () -> locationUseCase.save(validLocation));
    }

    @Test
    void getLocations_WithBlankName_ShouldReturnAllLocations() {
        // Arrange
        List<LocationModel> mockLocations = List.of(TestDataLocation.getValidLocation());
        when(locationPersistencePort.getAllLocations()).thenReturn(mockLocations);

        // Act
        Pagination<LocationModel> result = locationUseCase.getLocations("", 1, 10, "city", true);

        // Assert
        assertEquals(1, result.getContent().size());
        verify(locationPersistencePort).getAllLocations();
    }

    @Test
    void getLocations_WithDepartmentName_ShouldReturnFilteredLocations() {
        // Arrange
        List<LocationModel> mockLocations = List.of(TestDataLocation.getValidLocation());
        when(departmentPersistencePort.findByName(any())).thenReturn(Optional.of(TestDataLocation.getValidDepartment()));
        when(locationPersistencePort.findAllByDepartmentName(any())).thenReturn(mockLocations);

        // Act
        Pagination<LocationModel> result = locationUseCase.getLocations("DEPARTMENT", 1, 10, "department", true);

        // Assert
        assertEquals(1, result.getContent().size());
        verify(locationPersistencePort).findAllByDepartmentName(any());
    }

    @Test
    void getLocations_WithCityName_ShouldReturnFilteredLocations() {
        // Arrange
        List<LocationModel> mockLocations = List.of(TestDataLocation.getValidLocation());
        when(departmentPersistencePort.findByName(any())).thenReturn(Optional.empty());
        when(locationPersistencePort.findAllByCityName(any())).thenReturn(mockLocations);

        // Act
        Pagination<LocationModel> result = locationUseCase.getLocations("CITY", 1, 10, "city", true);

        // Assert
        assertEquals(1, result.getContent().size());
        verify(locationPersistencePort).findAllByCityName(any());
    }

    @Test
    void getLocations_WithInvalidOrderBy_ShouldThrowException() {
        // Arrange
        List<LocationModel> mockLocations = List.of(TestDataLocation.getValidLocation());
        when(locationPersistencePort.getAllLocations()).thenReturn(mockLocations);

        // Act & Assert
        assertThrows(LocationOrderNotFoundException.class,
                () -> locationUseCase.getLocations("", 1, 10, "invalid", true));
    }
}
