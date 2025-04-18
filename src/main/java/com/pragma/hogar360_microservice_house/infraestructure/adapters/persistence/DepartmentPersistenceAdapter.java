package com.pragma.hogar360_microservice_house.infraestructure.adapters.persistence;

import com.pragma.hogar360_microservice_house.domain.model.DepartmentModel;
import com.pragma.hogar360_microservice_house.domain.ports.out.IDepartmentPersistencePort;
import com.pragma.hogar360_microservice_house.infraestructure.mappers.IDepartmentEntityMapper;
import com.pragma.hogar360_microservice_house.infraestructure.repositories.mysql.IDepartmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DepartmentPersistenceAdapter implements IDepartmentPersistencePort {

    private final IDepartmentRepository departmentRepository;
    private final IDepartmentEntityMapper departmentEntityMapper;

    @Override
    public DepartmentModel save(DepartmentModel departmentModel) {
        return departmentEntityMapper.entityToModel(departmentRepository.save(departmentEntityMapper.modelToEntity(departmentModel)));
    }

    @Override
    public Optional<DepartmentModel> findByName(String nameDepartment) {
        return departmentEntityMapper.entityOptionalToModelOptional(departmentRepository.findByName(nameDepartment));
    }
}
