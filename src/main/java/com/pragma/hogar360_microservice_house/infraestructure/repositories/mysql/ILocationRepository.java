package com.pragma.hogar360_microservice_house.infraestructure.repositories.mysql;

import com.pragma.hogar360_microservice_house.infraestructure.entities.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ILocationRepository extends JpaRepository<LocationEntity, Long> {

    Optional<LocationEntity> findByNeighborhoodAndCityEntityId(String neighborhood, Long cityId);
    Optional<LocationEntity> findByNeighborhoodAndCityEntityNameAndCityEntityDepartmentEntityName(
            String neighborhood, String cityName, String departmentName
    );

    @Query("SELECT l FROM LocationEntity l " +
            "JOIN FETCH l.cityEntity c " +
            "JOIN FETCH c.departmentEntity d " +
            "WHERE (:nameLocation IS NULL OR " +
            "       c.name LIKE CONCAT('%', :nameLocation, '%') OR " +
            "       d.name LIKE CONCAT('%', :nameLocation, '%'))")
    List<LocationEntity> findByCityOrDepartment(@Param("nameLocation") String nameLocation);
}
