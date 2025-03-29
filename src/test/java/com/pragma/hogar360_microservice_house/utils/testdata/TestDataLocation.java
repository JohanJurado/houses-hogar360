package com.pragma.hogar360_microservice_house.utils.testdata;

import com.pragma.hogar360_microservice_house.domain.model.*;
import java.util.List;

import static com.pragma.hogar360_microservice_house.utils.constants.LocationTestConstants.*;

public class TestDataLocation {

    public static DepartmentModel getValidDepartment() {
        DepartmentModel department = new DepartmentModel();
        department.setId(VALID_DEPARTMENT_ID);
        department.setName(VALID_DEPARTMENT_NAME);
        department.setDescription(VALID_DEPARTMENT_DESCRIPTION);
        return department;
    }

    public static DepartmentModel getSecondDepartment() {
        DepartmentModel department = new DepartmentModel();
        department.setId(VALID_DEPARTMENT_ID_2);
        department.setName(VALID_DEPARTMENT_NAME_2);
        department.setDescription(VALID_DEPARTMENT_DESCRIPTION);
        return department;
    }

    public static CityModel getValidCity() {
        CityModel city = new CityModel();
        city.setId(VALID_CITY_ID);
        city.setName(VALID_CITY_NAME);
        city.setDescription(VALID_CITY_DESCRIPTION);
        city.setDepartmentModel(getValidDepartment());
        return city;
    }

    public static CityModel getCityWithDepartment(DepartmentModel department) {
        CityModel city = new CityModel();
        city.setId(VALID_CITY_ID);
        city.setName(VALID_CITY_NAME);
        city.setDescription(VALID_CITY_DESCRIPTION);
        city.setDepartmentModel(department);
        return city;
    }

    public static LocationModel getValidLocation() {
        LocationModel location = new LocationModel();
        location.setId(VALID_LOCATION_ID);
        location.setCityModel(getValidCity());
        location.setNeighborhood(VALID_NEIGHBORHOOD);
        return location;
    }

    public static LocationModel getLocationWithLongNeighborhood() {
        LocationModel location = getValidLocation();
        location.setNeighborhood(INVALID_LONG_NEIGHBORHOOD);
        return location;
    }

    public static LocationModel getLocationWithEmptyNeighborhood() {
        LocationModel location = getValidLocation();
        location.setNeighborhood(EMPTY_STRING);
        return location;
    }

    public static List<LocationModel> getMultipleLocations() {
        return List.of(
                getValidLocation(),
                getValidLocation(),
                getValidLocation()
        );
    }
}