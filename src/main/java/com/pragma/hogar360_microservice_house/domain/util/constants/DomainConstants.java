package com.pragma.hogar360_microservice_house.domain.util.constants;

import static com.pragma.hogar360_microservice_house.domain.util.constants.GlobalConstants.UTILITY_CLASS_MESSAGE;

public final class DomainConstants {

    private DomainConstants() {
        throw new IllegalStateException(UTILITY_CLASS_MESSAGE);
    }

    // category
    public static final Long MAX_NAME_SIZE_CATEGORY = 50L;
    public static final Long MAX_DESCRIPTION_SIZE_CATEGORY = 90L;

    // location
    public static final Long MAX_NEIGHBORHOOD_SIZE_LOCATION = 120L;
    public static final Long MAX_NAME_SIZE_CITY = 50L;
    public static final Long MAX_NAME_SIZE_DEPARTMENT = 50L;
    public static final Long MAX_DESCRIPTION_SIZE_CITY = 120L;
    public static final Long MAX_DESCRIPTION_SIZE_DEPARTMENT = 120L;

    // validation
    public static final String VALIDATIONS_STR_FROM_NULL_TO_BLANK = "";
    public static final String VALIDATIONS_STR_REGEX = "\\p{M}";
    public static final String VALIDATIONS_STR_REGEX_TO_BLANK = "";
    public static final Integer ONE_MONTH = 1;
}
