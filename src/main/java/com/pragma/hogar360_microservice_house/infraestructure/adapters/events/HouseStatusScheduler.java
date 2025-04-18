package com.pragma.hogar360_microservice_house.infraestructure.adapters.events;

import com.pragma.hogar360_microservice_house.domain.model.HouseModel;
import com.pragma.hogar360_microservice_house.domain.ports.in.events.IUpdateHouseStatusServicePort;
import com.pragma.hogar360_microservice_house.domain.ports.out.IHousePersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import java.util.List;

import static com.pragma.hogar360_microservice_house.domain.util.constants.StateHousesConstants.PAUSED_STATE_HOUSE;
import static com.pragma.hogar360_microservice_house.infraestructure.adapters.events.EventConstants.SCHEDULER_BY_DAY;

@RequiredArgsConstructor
@Component
public class HouseStatusScheduler {
    private final IUpdateHouseStatusServicePort updateHouseStatusServicePort;
    private final IHousePersistencePort housePersistencePort;

    @Scheduled(cron = SCHEDULER_BY_DAY)
    public void updatePausedHouses() {

        List<HouseModel> pausedHouses = housePersistencePort
                .findByPublicationStatusAndActivePublicationDate(PAUSED_STATE_HOUSE, LocalDate.now());

        pausedHouses.forEach(house ->
                updateHouseStatusServicePort.updateHouseStatusIfNeeded(house.getId())
        );
    }
}
