package com.pragma.hogar360_microservice_house.infraestructure.entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Table(name="city")
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DepartmentEntity> departmentEntityList;

    public CityEntity(Long id, String name, String description, List<DepartmentEntity> departmentEntityList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.departmentEntityList = departmentEntityList;
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

    public List<DepartmentEntity> getDepartmentEntityList() {
        return departmentEntityList;
    }

    public void setDepartmentEntityList(List<DepartmentEntity> departmentEntityList) {
        this.departmentEntityList = departmentEntityList;
    }
}
