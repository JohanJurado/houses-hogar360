package com.pragma.hogar360_microservice_house.infraestructure.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "house")
public class HouseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Long bedroomCount;
    private Long bathroomCount;
    private Double price;
    private LocalDate activePublicationDate;
    private LocalDate publicationDate;
    private String publicationStatus;
    private String emailSeller;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private LocationEntity locationEntity;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;

    public HouseEntity() {
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

    public String getEmailSeller() {
        return emailSeller;
    }

    public void setEmailSeller(String emailSeller) {
        this.emailSeller = emailSeller;
    }

    public LocationEntity getLocationEntity() {
        return locationEntity;
    }

    public void setLocationEntity(LocationEntity locationEntity) {
        this.locationEntity = locationEntity;
    }

    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }
}
