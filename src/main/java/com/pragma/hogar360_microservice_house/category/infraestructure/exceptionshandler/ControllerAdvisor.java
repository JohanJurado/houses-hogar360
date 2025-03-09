package com.pragma.hogar360_microservice_house.category.infraestructure.exceptionshandler;

import com.pragma.hogar360_microservice_house.category.domain.exceptions.CategoryAlreadyExistsExceptions;
import com.pragma.hogar360_microservice_house.category.domain.exceptions.DescriptionMaxSizeException;
import com.pragma.hogar360_microservice_house.category.domain.exceptions.NameMaxSizeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(NameMaxSizeException.class)
    public ResponseEntity<ExceptionResponse> nameMaxSizeException(NameMaxSizeException exception){
        return ResponseEntity.badRequest().body(
                new ExceptionResponse(
                    ExceptionConstants.NAME_MAX_SIZE_MESSAGE, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(DescriptionMaxSizeException.class)
    public ResponseEntity<ExceptionResponse> descriptionMaxSizeException(DescriptionMaxSizeException exception){
        return ResponseEntity.badRequest().body(
                new ExceptionResponse(
                    ExceptionConstants.DESCRIPTION_MAX_SIZE_MESSAGE, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(CategoryAlreadyExistsExceptions.class)
    public ResponseEntity<ExceptionResponse> categoryAlreadyExistsExceptions(CategoryAlreadyExistsExceptions exception){
        return ResponseEntity.badRequest().body(
                new ExceptionResponse(
                    ExceptionConstants.CATEGORY_EXIST_MESSAGE, LocalDateTime.now()
                )
        );
    }
}
