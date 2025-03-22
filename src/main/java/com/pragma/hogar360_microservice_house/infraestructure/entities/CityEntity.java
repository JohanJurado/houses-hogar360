package com.pragma.hogar360_microservice_house.infraestructure.entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name="city")
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentEntity departmentEntity;

    public CityEntity(Long id, String name, String description, DepartmentEntity departmentEntity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.departmentEntity = departmentEntity;
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

    public DepartmentEntity getDepartmentEntity() {
        return departmentEntity;
    }

    public void setDepartmentEntity(DepartmentEntity departmentEntity) {
        this.departmentEntity = departmentEntity;
    }
}
