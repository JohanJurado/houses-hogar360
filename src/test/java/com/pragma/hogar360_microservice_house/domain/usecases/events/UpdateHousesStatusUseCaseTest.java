package com.pragma.hogar360_microservice_house.domain.usecases.events;

import com.pragma.hogar360_microservice_house.domain.exceptions.HouseNotFoundException;
import com.pragma.hogar360_microservice_house.domain.model.HouseModel;
import com.pragma.hogar360_microservice_house.domain.ports.out.IHousePersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.pragma.hogar360_microservice_house.utils.constants.UpdateHouseStateConstants.*;
import static com.pragma.hogar360_microservice_house.utils.testdata.UpdateHouseStatusTestData.createPausedHouseWithActiveDate;
import static com.pragma.hogar360_microservice_house.utils.testdata.UpdateHouseStatusTestData.createPublishedHouse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateHousesStatusUseCaseTest {

    @Mock
    private IHousePersistencePort housePersistencePort;

    @InjectMocks
    private UpdateHousesStatusUseCase updateHousesStatusUseCase;

    @Test
    void updateHouseStatusIfNeeded_WhenPausedAndActiveDateToday_ShouldPublish() {
        // Arrange
        HouseModel house = createPausedHouseWithActiveDate(TODAY);
        when(housePersistencePort.findById(VALID_HOUSE_ID)).thenReturn(Optional.of(house));

        // Act
        updateHousesStatusUseCase.updateHouseStatusIfNeeded(VALID_HOUSE_ID);

        // Assert
        verify(housePersistencePort).save(house);
        assertEquals(PUBLISHED_STATE, house.getPublicationStatus());
    }

    @Test
    void updateHouseStatusIfNeeded_WhenPublished_ShouldNotChange() {
        // Arrange
        HouseModel house = createPublishedHouse();
        when(housePersistencePort.findById(VALID_HOUSE_ID)).thenReturn(Optional.of(house));

        // Act
        updateHousesStatusUseCase.updateHouseStatusIfNeeded(VALID_HOUSE_ID);

        // Assert
        verify(housePersistencePort, never()).save(house);
        assertEquals(PUBLISHED_STATE, house.getPublicationStatus());
    }

    @Test
    void updateHouseStatusIfNeeded_WhenPausedButFutureDate_ShouldNotChange() {
        // Arrange
        HouseModel house = createPausedHouseWithActiveDate(TOMORROW);
        when(housePersistencePort.findById(VALID_HOUSE_ID)).thenReturn(Optional.of(house));

        // Act
        updateHousesStatusUseCase.updateHouseStatusIfNeeded(VALID_HOUSE_ID);

        // Assert
        verify(housePersistencePort, never()).save(house);
        assertEquals(PAUSED_STATE, house.getPublicationStatus());
    }

    @Test
    void updateHouseStatusIfNeeded_WhenPausedButPastDate_ShouldNotChange() {
        // Arrange
        HouseModel house = createPausedHouseWithActiveDate(YESTERDAY);
        when(housePersistencePort.findById(VALID_HOUSE_ID)).thenReturn(Optional.of(house));

        // Act
        updateHousesStatusUseCase.updateHouseStatusIfNeeded(VALID_HOUSE_ID);

        // Assert
        verify(housePersistencePort, never()).save(house);
        assertEquals(PAUSED_STATE, house.getPublicationStatus());
    }

    @Test
    void updateHouseStatusIfNeeded_WhenHouseNotFound_ShouldThrowException() {
        // Arrange
        when(housePersistencePort.findById(INVALID_HOUSE_ID)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(HouseNotFoundException.class, () -> {
            updateHousesStatusUseCase.updateHouseStatusIfNeeded(INVALID_HOUSE_ID);
        });
    }
}