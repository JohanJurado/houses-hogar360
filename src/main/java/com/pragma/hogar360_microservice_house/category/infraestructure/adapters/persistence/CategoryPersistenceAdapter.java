package com.pragma.hogar360_microservice_house.category.infraestructure.adapters.persistence;

import com.pragma.hogar360_microservice_house.category.domain.model.CategoryModel;
import com.pragma.hogar360_microservice_house.category.domain.ports.out.ICategoryPersistencePort;
import com.pragma.hogar360_microservice_house.category.infraestructure.mappers.ICategoryEntityMapper;
import com.pragma.hogar360_microservice_house.category.infraestructure.repositories.mysql.ICategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryPersistenceAdapter implements ICategoryPersistencePort {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Override
    public void save(CategoryModel categoryModel) {
        categoryRepository.save(categoryEntityMapper.modelToEntity(categoryModel));
    }

    @Override
    public CategoryModel findByName(String name) {
        return categoryEntityMapper.entityToModel(categoryRepository.findByName(name));
    }
}
