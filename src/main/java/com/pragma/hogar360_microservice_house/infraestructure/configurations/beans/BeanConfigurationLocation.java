package com.pragma.hogar360_microservice_house.infraestructure.configurations.beans;

import com.pragma.hogar360_microservice_house.domain.ports.in.ILocationServicePort;
import com.pragma.hogar360_microservice_house.domain.ports.out.ILocationPersistencePort;
import com.pragma.hogar360_microservice_house.domain.usecases.LocationUseCase;
import com.pragma.hogar360_microservice_house.infraestructure.adapters.persistence.LocationPersistenceAdapter;
import com.pragma.hogar360_microservice_house.infraestructure.mappers.ILocationEntityMapper;
import com.pragma.hogar360_microservice_house.infraestructure.repositories.mysql.ICityRepository;
import com.pragma.hogar360_microservice_house.infraestructure.repositories.mysql.IDepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfigurationLocation {

    private final ICityRepository cityRepository;
    private final IDepartmentRepository departmentRepository;
    private final ILocationEntityMapper locationEntityMapper;

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
}
