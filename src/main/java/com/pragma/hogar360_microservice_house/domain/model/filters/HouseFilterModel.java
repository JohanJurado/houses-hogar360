package com.pragma.hogar360_microservice_house.domain.model.filters;

public class HouseFilterModel {

    private String neighborhood;
    private String nameCity;
    private String nameDepartment;
    private String nameCategory;
    private Long bedroomCount;
    private Long bathroomCount;
    private Double minPrice;
    private Double maxPrice;

    public HouseFilterModel() {
        // Inserting data into Setters
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public Long getBedroomCount() {
        return bedroomCount;
    }

    public void setBedroomCount(Long bedroomCount) {
        this.bedroomCount = bedroomCount;
    }

    public Long getBathroomCount() {
        return bathroomCount;
    }

    public void setBathroomCount(Long bathroomCount) {
        this.bathroomCount = bathroomCount;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }
}
