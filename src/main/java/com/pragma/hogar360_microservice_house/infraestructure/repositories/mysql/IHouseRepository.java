package com.pragma.hogar360_microservice_house.infraestructure.repositories.mysql;

import com.pragma.hogar360_microservice_house.domain.model.filters.HouseFilterModel;
import com.pragma.hogar360_microservice_house.infraestructure.entities.HouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IHouseRepository extends JpaRepository<HouseEntity, Long> {

    @Query("SELECT h FROM HouseEntity h " +
            "WHERE (:#{#filter.nameCity} IS NULL OR h.locationEntity.cityEntity.name LIKE UPPER(CONCAT('%', :#{#filter.nameCity}, '%'))) " +
            "AND (:#{#filter.neighborhood} IS NULL OR h.locationEntity.neighborhood LIKE UPPER(CONCAT('%', :#{#filter.neighborhood}, '%'))) " +
            "AND (:#{#filter.nameDepartment} IS NULL OR h.locationEntity.cityEntity.departmentEntity.name LIKE UPPER(CONCAT('%', :#{#filter.nameDepartment}, '%'))) " +
            "AND (:#{#filter.nameCategory} IS NULL OR h.categoryEntity.name LIKE UPPER(CONCAT('%', :#{#filter.nameCategory}, '%'))) " +
            "AND (:#{#filter.bedroomCount} IS NULL OR h.bedroomCount = :#{#filter.bedroomCount}) " +
            "AND (:#{#filter.bathroomCount} IS NULL OR h.bathroomCount = :#{#filter.bathroomCount}) " +
            "AND (:#{#filter.minPrice} IS NULL OR h.price >= :#{#filter.minPrice}) " +
            "AND (:#{#filter.maxPrice} IS NULL OR h.price <= :#{#filter.maxPrice}) " +
            "AND h.publicationStatus = :publicationStatus")
    List<HouseEntity> findHousesByFilters(@Param("filter") HouseFilterModel filter, @Param("publicationStatus") String publicationStatus);

    Optional<HouseEntity> findById(Long id);
    List<HouseEntity> findByPublicationStatusAndActivePublicationDate(String publicationStatus, LocalDate activePublicationDate);

    boolean existsByIdAndEmailSeller(Long id, String emailSeller);
}
