package com.pragma.hogar360_microservice_house.domain.model;

public class LocationModel {

    private Long id;
    private CityModel cityModel;
    private String neighborhood;

    public LocationModel() {
        // Inserting data into Setters
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CityModel getCityModel() {
        return cityModel;
    }

    public void setCityModel(CityModel cityModel) {
        this.cityModel = cityModel;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }
}
