package com.pragma.hogar360_microservice_house.infraestructure.configurations.beans;

import com.pragma.hogar360_microservice_house.domain.ports.in.ICategoryServicePort;
import com.pragma.hogar360_microservice_house.domain.ports.out.ICategoryPersistencePort;
import com.pragma.hogar360_microservice_house.domain.usecases.CategoryUseCase;
import com.pragma.hogar360_microservice_house.infraestructure.adapters.persistence.CategoryPersistenceAdapter;
import com.pragma.hogar360_microservice_house.infraestructure.mappers.ICategoryEntityMapper;
import com.pragma.hogar360_microservice_house.infraestructure.repositories.mysql.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfigurationCategory {

    private final ICategoryEntityMapper categoryEntityMapper;
    private final ICategoryRepository categoryRepository;

    @Bean
    public ICategoryServicePort categoryServicePort(){
        return new CategoryUseCase(categoryPersistencePort());
    }

    @Bean
    public ICategoryPersistencePort categoryPersistencePort(){
        return new CategoryPersistenceAdapter(categoryRepository, categoryEntityMapper);
    }
}
