package com.pragma.hogar360_microservice_house.domain.util.constants;

import static com.pragma.hogar360_microservice_house.domain.util.constants.GlobalConstants.UTILITY_CLASS_MESSAGE;

public class StateHousesConstants {
    private StateHousesConstants() {
        throw new IllegalStateException(UTILITY_CLASS_MESSAGE);
    }

    public static final String PUBLISHED_STATE_HOUSE = "PUBLISHED";
    public static final String PAUSED_STATE_HOUSE = "PAUSED";
    public static final String TRANSACTION_IN_PROGRESS_STATE_HOUSE = "TRANSACTION_IN_PROGRESS";
    public static final String TRANSACTION_COMPLETED_STATE_HOUSE = "TRANSACTION_COMPLETED";
}
