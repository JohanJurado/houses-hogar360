package com.pragma.hogar360_microservice_house.infraestructure.exceptionshandler;

import com.pragma.hogar360_microservice_house.domain.exceptions.*;
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
    public ResponseEntity<ExceptionResponse> pageNotFoundException(PageNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionResponse(ExceptionConstants.PAGE_NOT_FOUND_MESSAGE, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(DepartmentAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> departmentAlreadyExistsException(DepartmentAlreadyExistsException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionResponse(ExceptionConstants.DEPARTMENT_EXIST_MESSAGE, LocalDateTime.now()
                )
        );
    }

}
