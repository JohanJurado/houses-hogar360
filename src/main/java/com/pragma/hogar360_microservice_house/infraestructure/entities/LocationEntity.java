package com.pragma.hogar360_microservice_house.infraestructure.entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "location")
public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String neighborhood;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityEntity cityEntity;

    public LocationEntity(Long id, String neighborhood, CityEntity cityEntity) {
        this.id = id;
        this.neighborhood = neighborhood;
        this.cityEntity = cityEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public CityEntity getCityEntity() {
        return cityEntity;
    }

    public void setCityEntity(CityEntity cityEntity) {
        this.cityEntity = cityEntity;
    }
}
