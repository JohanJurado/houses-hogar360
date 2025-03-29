//package com.pragma.hogar360_microservice_house.utils;
//import com.pragma.hogar360_microservice_house.domain.model.*;
//
//import java.time.LocalDate;
//import java.util.List;
//
//public class TestDataHouse {
//
//    public static final LocalDate CURRENT_DATE = LocalDate.now();
//    public static final LocalDate FUTURE_DATE = CURRENT_DATE.plusDays(10);
//    public static final LocalDate PAST_DATE = CURRENT_DATE.minusDays(5);
//
//    public static final Long VALID_ID = 1L;
//    public static final Object NULL_VALUE = null;
//
//    public static final String VALID_NAME = "Casa Moderna";
//    public static final String EMPTY_STRING = "";
//    public static final String BLANK_STRING = "   ";
//    public static final String VALID_DESCRIPTION = "Hermosa casa con 3 habitaciones y 2 baños";
//    public static final String VALID_CITY_NAME = "Bogotá";
//    public static final String VALID_DEPARTMENT_NAME = "Cundinamarca";
//    public static final String INVALID_DEPARTMENT_NAME = "";
//    public static final String VALID_CATEGORY_NAME = "Apartamento";
//
//    public static final Long VALID_BEDROOM_COUNT = 3L;
//    public static final Long VALID_BATHROOM_COUNT = 2L;
//    public static final Double VALID_PRICE = 1500000.0;
//
//    public static final Integer PAGE_PAGINATION = 0;
//    public static final Integer PAGE_NOT_FOUND_PAGINATION = 100;
//    public static final String NAME_LOCATION_BLANK_PAGINATION = "";
//    public static final Integer SIZE_PAGINATION = 10;
//    public static final boolean ORDER_ASC_PAGINATION = true;
//
//    public static final String DEPARTMENT_ORDER_BY_PAGINATION = "department";
//    public static final String CITY_ORDER_BY_PAGINATION = "city";
//    public static final String CATEGORY_ORDER_BY_PAGINATION = "category";
//    public static final String BEDROOM_ORDER_BY_PAGINATION = "bedroomCount";
//    public static final String BATHROOM_ORDER_BY_PAGINATION = "bathroomCount";
//    public static final String PRICE_ORDER_BY_PAGINATION = "price";
//
//    public static final String ORDER_BY_OTHER_PAGINATION = "other";
//
//    public static final Integer EMPTY_SIZE_LIST = 0;
//
//    public static DepartmentModel getValidDepartment() {
//        DepartmentModel department = new DepartmentModel();
//        department.setId(VALID_ID);
//        department.setName(VALID_DEPARTMENT_NAME);
//        return department;
//    }
//
//    public static DepartmentModel getInvalidDepartment() {
//        DepartmentModel department = new DepartmentModel();
//        department.setId(VALID_ID);
//        department.setName(INVALID_DEPARTMENT_NAME);
//        return department;
//    }
//
//    public static CityModel getValidCity() {
//        CityModel city = new CityModel();
//        city.setId(VALID_ID);
//        city.setName(VALID_CITY_NAME);
//        city.setDepartmentModel(getValidDepartment());
//        return city;
//    }
//
//    public static CategoryModel getValidCategory() {
//        CategoryModel category = new CategoryModel();
//        category.setId(VALID_ID);
//        category.setName(VALID_CATEGORY_NAME);
//        return category;
//    }
//
//    public static HouseModel getValidHouse() {
//        HouseModel house = new HouseModel();
//        house.setId(VALID_ID);
//        house.setName(VALID_NAME);
//        house.setDescription(VALID_DESCRIPTION);
//        house.setBedroomCount(VALID_BEDROOM_COUNT);
//        house.setBathroomCount(VALID_BATHROOM_COUNT);
//        house.setPrice(VALID_PRICE);
//        house.setActivePublicationDate(CURRENT_DATE);
//        house.setCityModel(getValidCity());
//        house.setCategoryModel(getValidCategory());
//        return house;
//    }
//
//    public static HouseModel getHouseWithEmptyName() {
//        HouseModel house = getValidHouse();
//        house.setName(EMPTY_STRING);
//        return house;
//    }
//
//    public static HouseModel getHouseWithNullBedroomCount() {
//        HouseModel house = getValidHouse();
//        house.setBedroomCount((Long) NULL_VALUE);
//        return house;
//    }
//
//    public static HouseModel getHouseWithPublishedState() {
//        HouseModel house = getValidHouse();
//        house.setPublicationDate(CURRENT_DATE);
//        house.setActivePublicationDate(CURRENT_DATE);
//        return house;
//    }
//
//    public static HouseModel getHouseWithPausedState() {
//        HouseModel house = getValidHouse();
//        house.setPublicationDate(CURRENT_DATE);
//        house.setActivePublicationDate(FUTURE_DATE);
//        return house;
//    }
//
//    public static HouseModel getHouseWithEmptyCityName() {
//        HouseModel house = getValidHouse();
//        house.getCityModel().setName(EMPTY_STRING);
//        return house;
//    }
//
//    public static HouseModel getHouseWithEmptyDepartmentName() {
//        HouseModel house = getValidHouse();
//        house.getCityModel().getDepartmentModel().setName(EMPTY_STRING);
//        return house;
//    }
//
//    public static HouseModel getHouseWithEmptyCategoryName() {
//        HouseModel house = getValidHouse();
//        house.getCategoryModel().setName(EMPTY_STRING);
//        return house;
//    }
//
//    public static List<CityModel> getValidCityList() {
//        return List.of(getValidCity());
//    }
//
//    public static List<CityModel> getEmptyCityList() {
//        return List.of();
//    }
//}