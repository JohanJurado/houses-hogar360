package com.pragma.hogar360_microservice_house.infraestructure.configurations.beans;

import com.pragma.hogar360_microservice_house.domain.ports.in.ICategoryServicePort;
import com.pragma.hogar360_microservice_house.domain.ports.in.IHouseServicePort;
import com.pragma.hogar360_microservice_house.domain.ports.in.ILocationServicePort;
import com.pragma.hogar360_microservice_house.domain.ports.out.ICategoryPersistencePort;
import com.pragma.hogar360_microservice_house.domain.ports.out.IHousePersistencePort;
import com.pragma.hogar360_microservice_house.domain.ports.out.ILocationPersistencePort;
import com.pragma.hogar360_microservice_house.domain.usecases.CategoryUseCase;
import com.pragma.hogar360_microservice_house.domain.usecases.HouseUseCase;
import com.pragma.hogar360_microservice_house.domain.usecases.LocationUseCase;
import com.pragma.hogar360_microservice_house.infraestructure.adapters.persistence.CategoryPersistenceAdapter;
import com.pragma.hogar360_microservice_house.infraestructure.adapters.persistence.HousePersistenceAdapter;
import com.pragma.hogar360_microservice_house.infraestructure.adapters.persistence.LocationPersistenceAdapter;
import com.pragma.hogar360_microservice_house.infraestructure.mappers.ICategoryEntityMapper;
import com.pragma.hogar360_microservice_house.infraestructure.mappers.IHouseEntityMapper;
import com.pragma.hogar360_microservice_house.infraestructure.mappers.ILocationEntityMapper;
import com.pragma.hogar360_microservice_house.infraestructure.repositories.mysql.ICategoryRepository;
import com.pragma.hogar360_microservice_house.infraestructure.repositories.mysql.ICityRepository;
import com.pragma.hogar360_microservice_house.infraestructure.repositories.mysql.IDepartmentRepository;
import com.pragma.hogar360_microservice_house.infraestructure.repositories.mysql.IHouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ICategoryEntityMapper categoryEntityMapper;
    private final ICategoryRepository categoryRepository;
    private final ICityRepository cityRepository;
    private final IDepartmentRepository departmentRepository;
    private final ILocationEntityMapper locationEntityMapper;
    private final IHouseEntityMapper houseEntityMapper;
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
        return new LocationUseCase(locationPersistencePort());
    }

    @Bean
    public ILocationPersistencePort locationPersistencePort(){
        return new LocationPersistenceAdapter(
                cityRepository, departmentRepository, locationEntityMapper
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
