package com.pragma.hogar360_microservice_house.utils;

import com.pragma.hogar360_microservice_house.domain.model.CityModel;
import com.pragma.hogar360_microservice_house.domain.model.DepartmentModel;
import com.pragma.hogar360_microservice_house.domain.model.LocationModel;

public class TestDataLocation {

    // Constantes generales
    public static final Long VALID_ID = 1L;
    public static final Long INVALID_ID = -1L;
    public static final String VALID_NAME = "CIUDAD EJEMPLO";
    public static final String VALID_DESCRIPTION = "Descripción válida";
    public static final String VALID_NEIGHBORHOOD = "BARRIO EJEMPLO";
    public static final String EMPTY_STRING = "";
    public static final String BLANK_STRING = "   ";

    // Tamaños máximos
    public static final int MAX_NAME_SIZE = 50;
    public static final int MAX_DESCRIPTION_SIZE = 200;
    public static final int MAX_NEIGHBORHOOD_SIZE = 100;

    // Objetos completos para testing
    public static DepartmentModel getValidDepartment() {
        DepartmentModel department = new DepartmentModel();
        department.setId(VALID_ID);
        department.setName(VALID_NAME);
        department.setDescription(VALID_DESCRIPTION);
        return department;
    }

    public static CityModel getValidCity() {
        CityModel city = new CityModel();
        city.setId(VALID_ID);
        city.setName(VALID_NAME);
        city.setDescription(VALID_DESCRIPTION);
        city.setDepartmentModel(getValidDepartment());
        return city;
    }

    public static LocationModel getValidLocation() {
        LocationModel location = new LocationModel();
        location.setId(VALID_ID);
        location.setNeighborhood(VALID_NEIGHBORHOOD);
        location.setCityModel(getValidCity());
        return location;
    }

    // Casos especiales para pruebas
    public static LocationModel getLocationWithEmptyNeighborhood() {
        LocationModel location = getValidLocation();
        location.setNeighborhood(EMPTY_STRING);
        return location;
    }

    public static LocationModel getLocationWithLongName() {
        LocationModel location = getValidLocation();
        location.getCityModel().setName("A".repeat(MAX_NAME_SIZE + 1));
        return location;
    }
}
