package com.pragma.hogar360_microservice_house.category.infraestructure.adapters.persistence;

import com.pragma.hogar360_microservice_house.category.domain.model.CategoryModel;
import com.pragma.hogar360_microservice_house.category.domain.ports.out.ICategoryPersistencePort;
import com.pragma.hogar360_microservice_house.category.infraestructure.mappers.ICategoryEntityMapper;
import com.pragma.hogar360_microservice_house.category.infraestructure.repositories.mysql.ICategoryRepository;
import com.pragma.hogar360_microservice_house.commons.configurations.utils.Constants;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<CategoryModel> findByName(String name) {
        return categoryEntityMapper.entityOptionalToModelOptional(categoryRepository.findByName(name));
    }

    @Override
    public List<CategoryModel> getAllCategories() {
        return categoryEntityMapper.entityListToModelList(categoryRepository.findAll());
    }
}
