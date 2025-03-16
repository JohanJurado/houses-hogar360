package com.pragma.hogar360_microservice_house.utils;

import com.pragma.hogar360_microservice_house.domain.model.CityModel;
import com.pragma.hogar360_microservice_house.domain.model.DepartmentModel;

import java.util.ArrayList;
import java.util.List;

public class TestDataLocation {

    public static CityModel getCityModel(){
        CityModel cityModel = new CityModel();
        cityModel.setId(1L);
        cityModel.setName("City 1");
        cityModel.setDescription("Description 1");
        return cityModel;
    }

    public static DepartmentModel getDepartmentModel(){
        DepartmentModel departmentModel = new DepartmentModel();
        departmentModel.setId(1L);
        departmentModel.setName("Department 1");
        departmentModel.setDescription("Description 1");
        departmentModel.setCity(getCityModel());
        return departmentModel;
    }

    public static CityModel getCityModelMaxName(){
        CityModel cityModel = getCityModel();
        cityModel.setName("1".repeat(51));
        return cityModel;
    }

    public static DepartmentModel getDepartmentModelMaxName(){
        DepartmentModel departmentModel = getDepartmentModel();
        departmentModel.setName("1".repeat(51));
        return departmentModel;
    }

    public static CityModel getCityModelMaxDescription(){
        CityModel cityModel = getCityModel();
        cityModel.setDescription("1".repeat(121));
        return cityModel;
    }

    public static DepartmentModel getDepartmentModelMaxDescription(){
        DepartmentModel departmentModel = getDepartmentModel();
        departmentModel.setDescription("1".repeat(121));
        return departmentModel;
    }

    public static CityModel getCityNameNull(){
        CityModel cityModel = getCityModel();
        cityModel.setName(null);
        return cityModel;
    }

    public static CityModel getCityNameBlank(){
        CityModel cityModel = getCityModel();
        cityModel.setName("");
        return cityModel;
    }

    public static DepartmentModel getDepartmentNameNull(){
        DepartmentModel departmentModel = getDepartmentModel();
        departmentModel.setName(null);
        return departmentModel;
    }

    public static DepartmentModel getDepartmentNameBlank(){
        DepartmentModel departmentModel = getDepartmentModel();
        departmentModel.setName("");
        return departmentModel;
    }

    public static CityModel getCityDescriptionNull(){
        CityModel cityModel = getCityModel();
        cityModel.setDescription(null);
        return cityModel;
    }

    public static CityModel getCityDescriptionBlank(){
        CityModel cityModel = getCityModel();
        cityModel.setDescription("");
        return cityModel;
    }

    public static DepartmentModel getDepartmentDescriptionNull(){
        DepartmentModel departmentModel = getDepartmentModel();
        departmentModel.setDescription(null);
        return departmentModel;
    }

    public static DepartmentModel getDepartmentDescriptionBlank(){
        DepartmentModel departmentModel = getDepartmentModel();
        departmentModel.setDescription("");
        return departmentModel;
    }

    public static String getNameLocation(){
        CityModel city = getCityModel();
        return city.getName();
    }

    public static List<DepartmentModel> getLocationsModels(){
        DepartmentModel department = getDepartmentModel();
        List<DepartmentModel> departmentModelList = new ArrayList<>();
        departmentModelList.add(department);
        departmentModelList.add(department);
        return departmentModelList;
    }

    public static List<DepartmentModel> getLocationsModelsSize1(){
        DepartmentModel department = getDepartmentModel();
        List<DepartmentModel> departmentModelList = new ArrayList<>();
        departmentModelList.add(department);
        return departmentModelList;
    }

    public static final Integer PAGE_PAGINATION = 1;
    public static final Integer PAGE_NOT_FOUND_PAGINATION = 0;
    public static final String NAME_LOCATION_BLANK_PAGINATION = "";
    public static final Integer SIZE_PAGINATION = 10;
    public static final String ORDER_BY_CITY_PAGINATION = "city";
    public static final String ORDER_BY_DEPARTMENT_PAGINATION = "department";
    public static final String ORDER_BY_OTHER_PAGINATION = "other";
    public static final boolean ORDER_ASC_PAGINATION = true;
    public static final boolean ORDER_DESC_PAGINATION = false;
}
