package com.pragma.hogar360_microservice_house.utils.constants;

import java.time.LocalDate;

public class UpdateHouseStateConstants {
    public static final Long VALID_HOUSE_ID = 1L;
    public static final Long INVALID_HOUSE_ID = 999L;

    public static final LocalDate TODAY = LocalDate.now();
    public static final LocalDate YESTERDAY = LocalDate.now().minusDays(1);
    public static final LocalDate TOMORROW = LocalDate.now().plusDays(1);

    public static final String PUBLISHED_STATE = "PUBLISHED";
    public static final String PAUSED_STATE = "PAUSED";
}
