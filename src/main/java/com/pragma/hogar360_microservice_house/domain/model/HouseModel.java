package com.pragma.hogar360_microservice_house.domain.model;

import java.time.LocalDate;

import static com.pragma.hogar360_microservice_house.domain.util.constants.StateHousesConstants.PAUSED_STATE_HOUSE;
import static com.pragma.hogar360_microservice_house.domain.util.constants.StateHousesConstants.PUBLISHED_STATE_HOUSE;

public class HouseModel {

    private Long id;
    private String name;
    private String description;
    private Long bedroomCount;
    private Long bathroomCount;
    private Double price;
    private LocalDate activePublicationDate;
    private LocalDate publicationDate;
    private String publicationStatus;

    private LocationModel locationModel;
    private CategoryModel categoryModel;

    public HouseModel() {
        // Inserting data into Setters
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getActivePublicationDate() {
        return activePublicationDate;
    }

    public void setActivePublicationDate(LocalDate activePublicationDate) {
        this.activePublicationDate = activePublicationDate;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getPublicationStatus() {
        return publicationStatus;
    }

    public void setPublicationStatus(String publicationStatus) {
        this.publicationStatus = publicationStatus;
    }

    public void calculateInitialStatus() {
        this.publicationStatus = (publicationDate.isBefore(activePublicationDate))
                ? PAUSED_STATE_HOUSE
                : PUBLISHED_STATE_HOUSE;
    }

    public LocationModel getLocationModel() {
        return locationModel;
    }

    public void setLocationModel(LocationModel locationModel) {
        this.locationModel = locationModel;
    }

    public CategoryModel getCategoryModel() {
        return categoryModel;
    }

    public void setCategoryModel(CategoryModel categoryModel) {
        this.categoryModel = categoryModel;
    }
}
