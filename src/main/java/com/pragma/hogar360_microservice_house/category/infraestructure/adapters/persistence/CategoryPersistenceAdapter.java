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

    @Override
    public List<CategoryModel> getAllCategories(Integer page, Integer size, boolean orderAsc) {
        Pageable pagination;

        if (orderAsc) pagination = PageRequest.of(page, size, Sort.by(Constants.PAGEABLE_FIELD_NAME).ascending());
        else pagination = PageRequest.of(page, size, Sort.by(Constants.PAGEABLE_FIELD_NAME).descending());

        return categoryEntityMapper.entityListToModelList(categoryRepository.findAll(pagination).getContent());
    }
}
