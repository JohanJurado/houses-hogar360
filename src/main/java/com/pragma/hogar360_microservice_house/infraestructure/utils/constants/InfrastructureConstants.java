package com.pragma.hogar360_microservice_house.infraestructure.utils.constants;

import static com.pragma.hogar360_microservice_house.domain.util.constants.GlobalConstants.UTILITY_CLASS_MESSAGE;

public class InfrastructureConstants {

    private InfrastructureConstants() {
        throw new IllegalStateException(UTILITY_CLASS_MESSAGE);
    }

    // role constants
    public static final String HAS_ROLE_ADMIN = "hasRole('ADMIN')";
    public static final String HAS_ROLE_SELLER = "hasRole('SELLER')";

    // claim keys
    public static final String AUTHORITIES_CLAIM_KEY = "authorities";

    // token constants
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final Integer TOKEN_PREFIX_SIZE = 7;

}
