package com.pragma.hogar360_microservice_house.infraestructure.configurations.beans;

import com.pragma.hogar360_microservice_house.domain.ports.in.ICategoryServicePort;
import com.pragma.hogar360_microservice_house.domain.ports.in.IHouseServicePort;
import com.pragma.hogar360_microservice_house.domain.ports.in.ILocationServicePort;
import com.pragma.hogar360_microservice_house.domain.ports.out.*;
import com.pragma.hogar360_microservice_house.domain.usecases.CategoryUseCase;
import com.pragma.hogar360_microservice_house.domain.usecases.HouseUseCase;
import com.pragma.hogar360_microservice_house.domain.usecases.LocationUseCase;
import com.pragma.hogar360_microservice_house.infraestructure.adapters.persistence.*;
import com.pragma.hogar360_microservice_house.infraestructure.mappers.*;
import com.pragma.hogar360_microservice_house.infraestructure.repositories.mysql.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ICategoryEntityMapper categoryEntityMapper;
    private final ILocationEntityMapper locationEntityMapper;
    private final ICityEntityMapper cityEntityMapper;
    private final IDepartmentEntityMapper departmentEntityMapper;
    private final IHouseEntityMapper houseEntityMapper;


    private final ICategoryRepository categoryRepository;
    private final ILocationRepository locationRepository;
    private final ICityRepository cityRepository;
    private final IDepartmentRepository departmentRepository;
    private final IHouseRepository houseRepository;

    // category
    @Bean
    public ICategoryServicePort categoryServicePort(){
        return new CategoryUseCase(categoryPersistencePort());
    }

    @Bean
    public ICategoryPersistencePort categoryPersistencePort(){
        return new CategoryPersistenceAdapter(categoryRepository, categoryEntityMapper);
    }

    // location
    @Bean
    public ILocationServicePort locationServicePort(){
        return new LocationUseCase(locationPersistencePort(), cityPersistencePort(), departmentPersistencePort());
    }

    @Bean
    public ILocationPersistencePort locationPersistencePort(){
        return new LocationPersistenceAdapter(
                locationRepository, locationEntityMapper
        );
    }

    @Bean
    public ICityPersistencePort cityPersistencePort(){
        return new CityPersistenceAdapter(
                cityRepository, cityEntityMapper
        );
    }

    @Bean
    public IDepartmentPersistencePort departmentPersistencePort(){
        return new DepartmentPersistenceAdapter(
                departmentRepository, departmentEntityMapper
        );
    }

    // house
    @Bean
    public IHouseServicePort houseServicePort(){
        return new HouseUseCase(housePersistencePort(), categoryPersistencePort(), locationPersistencePort());
    }

    @Bean
    public IHousePersistencePort housePersistencePort(){
        return new HousePersistenceAdapter(houseEntityMapper, houseRepository);
    }
}
