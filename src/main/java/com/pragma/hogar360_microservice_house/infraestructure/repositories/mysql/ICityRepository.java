package com.pragma.hogar360_microservice_house.infraestructure.repositories.mysql;

import com.pragma.hogar360_microservice_house.infraestructure.entities.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ICityRepository extends JpaRepository<CityEntity, Long> {

    Optional<CityEntity> findByNameAndDepartmentEntityId(String nameCity, Long departmentId);

    // --------
    @Query("SELECT c FROM CityEntity c WHERE c.name LIKE CONCAT('%', :nameCity, '%') AND c.departmentEntity.id = :idDepartment")
    List<CityEntity> findByMatches(@Param("nameCity") String nameCity, @Param("idDepartment") Long idDepartment);
}
