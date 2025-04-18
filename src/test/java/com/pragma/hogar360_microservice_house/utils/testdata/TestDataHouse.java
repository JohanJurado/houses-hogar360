package com.pragma.hogar360_microservice_house.utils.testdata;

import com.pragma.hogar360_microservice_house.domain.model.*;
import com.pragma.hogar360_microservice_house.domain.model.filters.HouseFilterModel;
import java.time.LocalDate;
import java.util.List;

import static com.pragma.hogar360_microservice_house.utils.constants.HouseTestConstants.*;

public class TestDataHouse {
    public static CategoryModel getValidCategory() {
        CategoryModel category = new CategoryModel();
        category.setId(VALID_CATEGORY_ID);
        category.setName(VALID_CATEGORY);
        return category;
    }

    public static LocationModel getValidLocation() {
        LocationModel location = new LocationModel();
        location.setId(VALID_LOCATION_ID);
        location.setNeighborhood(VALID_NEIGHBORHOOD);

        CityModel city = new CityModel();
        city.setName(VALID_CITY);

        DepartmentModel department = new DepartmentModel();
        department.setName(VALID_DEPARTMENT);

        city.setDepartmentModel(department);
        location.setCityModel(city);
        return location;
    }

    public static HouseModel getValidPublishedHouse() {
        HouseModel house = new HouseModel();
        house.setId(VALID_HOUSE_ID);
        house.setName(VALID_HOUSE_NAME);
        house.setDescription(VALID_HOUSE_DESCRIPTION);
        house.setBedroomCount(VALID_BEDROOM_COUNT);
        house.setBathroomCount(VALID_BATHROOM_COUNT);
        house.setPrice(VALID_PRICE);
        house.setPublicationDate(LocalDate.now());
        house.setActivePublicationDate(VALID_ACTIVE_DATE);
        house.setLocationModel(getValidLocation());
        house.setCategoryModel(getValidCategory());
        house.setPublicationStatus("PUBLISHED");
        return house;
    }

    public static HouseModel getHouseWithFutureActiveDate() {
        HouseModel house = getValidPublishedHouse();
        house.setActivePublicationDate(INVALID_FUTURE_ACTIVE_DATE);
        return house;
    }

    public static HouseModel getHouseWithEmptyName() {
        HouseModel house = getValidPublishedHouse();
        house.setName(EMPTY_STRING);
        return house;
    }

    public static HouseFilterModel getValidHouseFilter() {
        HouseFilterModel filter = new HouseFilterModel();
        filter.setNeighborhood(VALID_NEIGHBORHOOD);
        filter.setNameCity(VALID_CITY);
        filter.setNameDepartment(VALID_DEPARTMENT);
        filter.setNameCategory(VALID_CATEGORY);
        filter.setBedroomCount(BEDROOM_FILTER);
        filter.setBathroomCount(BATHROOM_FILTER);
        filter.setMinPrice(MIN_PRICE_FILTER);
        filter.setMaxPrice(MAX_PRICE_FILTER);
        return filter;
    }

    public static List<HouseModel> getMultipleHouses() {
        return List.of(
                getValidPublishedHouse(),
                createHouse("Apartamento moderno", 2L, 1L, 1200000.0, LocalDate.now().plusDays(2)),
                createHouse("Finca recreacional", 4L, 3L, 2500000.0, LocalDate.now().plusDays(3))
        );
    }

    private static HouseModel createHouse(String name, Long bedrooms, Long bathrooms, Double price, LocalDate activeDate) {
        HouseModel house = new HouseModel();
        house.setName(name);
        house.setBedroomCount(bedrooms);
        house.setBathroomCount(bathrooms);
        house.setPrice(price);
        house.setActivePublicationDate(activeDate);
        house.setPublicationDate(LocalDate.now());
        house.setLocationModel(getValidLocation());
        house.setCategoryModel(getValidCategory());
        house.setPublicationStatus("PUBLISHED");
        return house;
    }
}