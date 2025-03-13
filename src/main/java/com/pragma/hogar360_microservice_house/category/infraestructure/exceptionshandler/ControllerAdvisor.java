package com.pragma.hogar360_microservice_house.category.infraestructure.exceptionshandler;

import com.pragma.hogar360_microservice_house.category.domain.exceptions.CategoryAlreadyExistsException;
import com.pragma.hogar360_microservice_house.category.domain.exceptions.CategoryNotFoundException;
import com.pragma.hogar360_microservice_house.category.domain.exceptions.DescriptionMaxSizeException;
import com.pragma.hogar360_microservice_house.category.domain.exceptions.NameMaxSizeException;
import com.pragma.hogar360_microservice_house.commons.configurations.utils.Constants;
import com.pragma.hogar360_microservice_house.commons.configurations.utils.Pagination.PageNotFoundException;
import org.springframework.http.HttpStatus;
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

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> categoryAlreadyExistsExceptions(CategoryAlreadyExistsException exception){
        return ResponseEntity.badRequest().body(
                new ExceptionResponse(
                    ExceptionConstants.CATEGORY_EXIST_MESSAGE, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionResponse> nullPointerException(NullPointerException exception){
        return ResponseEntity.badRequest().body(new ExceptionResponse(exception.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ExceptionResponse> categoryNotFoundException(CategoryNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionResponse(ExceptionConstants.CATEGORY_NOT_FOUND_MESSAGE, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(PageNotFoundException.class)
    public ResponseEntity<ExceptionResponse> pageNotFound(PageNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionResponse(Constants.PAGE_NOT_FOUND_MESSAGE, LocalDateTime.now()
                )
        );
    }

}
