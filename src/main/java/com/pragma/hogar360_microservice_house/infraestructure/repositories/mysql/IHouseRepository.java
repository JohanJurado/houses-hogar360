package com.pragma.hogar360_microservice_house.infraestructure.repositories.mysql;

import com.pragma.hogar360_microservice_house.infraestructure.entities.HouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHouseRepository extends JpaRepository<HouseEntity, Long> {
}
