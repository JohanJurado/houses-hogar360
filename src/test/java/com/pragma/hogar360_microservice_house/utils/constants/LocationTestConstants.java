package com.pragma.hogar360_microservice_house.utils.constants;

public class LocationTestConstants {

    public static final Long VALID_LOCATION_ID = 1L;
    public static final Long VALID_CITY_ID = 1L;
    public static final Long VALID_DEPARTMENT_ID = 1L;
    public static final Long VALID_DEPARTMENT_ID_2 = 2L;

    public static final String VALID_NEIGHBORHOOD = "El Poblado";
    public static final String VALID_CITY_NAME = "Medellín";
    public static final String VALID_CITY_DESCRIPTION = "Ciudad de la eterna primavera";
    public static final String VALID_DEPARTMENT_NAME = "Antioquia";
    public static final String VALID_DEPARTMENT_NAME_2 = "Cundinamarca";
    public static final String VALID_DEPARTMENT_DESCRIPTION = "Departamento de Colombia";

    public static final String INVALID_LONG_NEIGHBORHOOD = "N".repeat(121);
    public static final String INVALID_LONG_CITY_NAME = "C".repeat(51);
    public static final String INVALID_LONG_DEPARTMENT_NAME = "D".repeat(51);
    public static final String INVALID_LONG_DESCRIPTION = "D".repeat(121);

    public static final String EMPTY_STRING = "";
    public static final String NULL_STRING = null;

    public static final String SEARCH_TERM_CITY = "medellin";
    public static final String SEARCH_TERM_DEPARTMENT = "antioquia";
    public static final String SEARCH_TERM_PARTIAL = "ell";
    public static final String SEARCH_TERM_WITH_ACCENTS = "medellín";
}