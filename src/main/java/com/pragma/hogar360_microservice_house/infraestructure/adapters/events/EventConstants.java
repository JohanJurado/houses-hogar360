package com.pragma.hogar360_microservice_house.infraestructure.adapters.events;

import static com.pragma.hogar360_microservice_house.domain.util.constants.GlobalConstants.UTILITY_CLASS_MESSAGE;

public class EventConstants {

    private EventConstants() {
        throw new IllegalStateException(UTILITY_CLASS_MESSAGE);
    }

    public static final String SCHEDULER_BY_DAY = "0 0 0 * * ?";
}
