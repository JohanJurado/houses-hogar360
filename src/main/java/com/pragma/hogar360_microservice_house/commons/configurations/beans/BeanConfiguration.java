package com.pragma.hogar360_microservice_house.commons.configurations.beans;

import com.pragma.hogar360_microservice_house.category.domain.ports.in.ICategoryServicePort;
import com.pragma.hogar360_microservice_house.category.domain.ports.out.ICategoryPersistencePort;
import com.pragma.hogar360_microservice_house.category.domain.usecases.CategoryUseCase;
import com.pragma.hogar360_microservice_house.category.infraestructure.adapters.persistence.CategoryPersistenceAdapter;
import com.pragma.hogar360_microservice_house.category.infraestructure.mappers.ICategoryEntityMapper;
import com.pragma.hogar360_microservice_house.category.infraestructure.repositories.mysql.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

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
