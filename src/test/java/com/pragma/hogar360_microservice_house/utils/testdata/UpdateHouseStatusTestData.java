package com.pragma.hogar360_microservice_house.utils.testdata;

import com.pragma.hogar360_microservice_house.domain.model.HouseModel;

import java.time.LocalDate;

import static com.pragma.hogar360_microservice_house.utils.constants.UpdateHouseStateConstants.*;

public class UpdateHouseStatusTestData {
    public static HouseModel createPausedHouseWithActiveDate(LocalDate activeDate) {
        HouseModel house = new HouseModel();
        house.setId(VALID_HOUSE_ID);
        house.setPublicationStatus(PAUSED_STATE);
        house.setActivePublicationDate(activeDate);
        return house;
    }

    public static HouseModel createPublishedHouse() {
        HouseModel house = new HouseModel();
        house.setId(VALID_HOUSE_ID);
        house.setPublicationStatus(PUBLISHED_STATE);
        house.setActivePublicationDate(YESTERDAY);
        return house;
    }

    public static HouseModel createHouseWithStatusAndDate(String status, LocalDate date) {
        HouseModel house = new HouseModel();
        house.setId(VALID_HOUSE_ID);
        house.setPublicationStatus(status);
        house.setActivePublicationDate(date);
        return house;
    }
}
