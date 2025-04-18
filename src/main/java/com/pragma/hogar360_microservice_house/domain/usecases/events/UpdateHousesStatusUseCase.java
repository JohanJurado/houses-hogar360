package com.pragma.hogar360_microservice_house.domain.usecases.events;

import com.pragma.hogar360_microservice_house.domain.exceptions.HouseNotFoundException;
import com.pragma.hogar360_microservice_house.domain.model.HouseModel;
import com.pragma.hogar360_microservice_house.domain.ports.in.events.IUpdateHouseStatusServicePort;
import com.pragma.hogar360_microservice_house.domain.ports.out.IHousePersistencePort;

import java.time.LocalDate;

import static com.pragma.hogar360_microservice_house.domain.util.constants.StateHousesConstants.PAUSED_STATE_HOUSE;
import static com.pragma.hogar360_microservice_house.domain.util.constants.StateHousesConstants.PUBLISHED_STATE_HOUSE;

public class UpdateHousesStatusUseCase implements IUpdateHouseStatusServicePort {

    private final IHousePersistencePort housePersistencePort;

    public UpdateHousesStatusUseCase(IHousePersistencePort housePersistencePort) {
        this.housePersistencePort = housePersistencePort;
    }

    @Override
    public void updateHouseStatusIfNeeded(Long houseId) {
        HouseModel house = housePersistencePort.findById(houseId)
                .orElseThrow(HouseNotFoundException::new);

        if (house.getPublicationStatus().equals(PAUSED_STATE_HOUSE)
                && LocalDate.now().isEqual(house.getActivePublicationDate())) {
            house.setPublicationStatus(PUBLISHED_STATE_HOUSE);
            housePersistencePort.save(house);
        }
    }
}
