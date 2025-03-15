package com.pragma.hogar360_microservice_house.utils;

import com.pragma.hogar360_microservice_house.domain.model.CityModel;
import com.pragma.hogar360_microservice_house.domain.model.DepartmentModel;

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
}
