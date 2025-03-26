package com.pragma.hogar360_microservice_house.infraestructure.repositories.mysql;

import com.pragma.hogar360_microservice_house.infraestructure.entities.HouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IHouseRepository extends JpaRepository<HouseEntity, Long> {

    @Query("SELECT h FROM HouseEntity h " +
            "WHERE (:nameCity IS NULL OR h.cityEntity.name LIKE UPPER(CONCAT('%', :nameCity, '%'))) " +
            "AND (:nameDepartment IS NULL OR h.cityEntity.departmentEntity.name LIKE UPPER(CONCAT('%', :nameDepartment, '%'))) " +
            "AND (:nameCategory IS NULL OR h.categoryEntity.name LIKE UPPER(CONCAT('%', :nameCategory, '%'))) " +
            "AND (:bedroomCount IS NULL OR h.bedroomCount = :bedroomCount) " +
            "AND (:bathroomCount IS NULL OR h.bathroomCount = :bathroomCount) " +
            "AND (:minPrice IS NULL OR h.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR h.price <= :maxPrice) " +
            "AND h.activePublicationDate <= CURRENT_DATE " +
            "AND h.publicationStatus = 'PUBLISHED'")
    List<HouseEntity> findHousesByFilters(
            @Param("nameCity") String nameCity,
            @Param("nameDepartment") String nameDepartment,
            @Param("nameCategory") String nameCategory,
            @Param("bedroomCount") Long bedroomCount,
            @Param("bathroomCount") Long bathroomCount,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice
    );


}
