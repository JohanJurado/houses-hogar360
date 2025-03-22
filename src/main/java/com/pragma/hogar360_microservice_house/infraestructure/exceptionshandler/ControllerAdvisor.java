package com.pragma.hogar360_microservice_house.infraestructure.exceptionshandler;

import com.pragma.hogar360_microservice_house.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(CategoryNameMaxSizeExceedException.class)
    public ResponseEntity<ExceptionResponse> nameMaxSizeException(CategoryNameMaxSizeExceedException exception){
        return ResponseEntity.badRequest().body(
                new ExceptionResponse(
                    ExceptionConstants.CATEGORY_NAME_MAX_SIZE_MESSAGE, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(CategoryDescriptionMaxSizeExceedException.class)
    public ResponseEntity<ExceptionResponse> descriptionMaxSizeException(CategoryDescriptionMaxSizeExceedException exception){
        return ResponseEntity.badRequest().body(
                new ExceptionResponse(
                    ExceptionConstants.CATEGORY_DESCRIPTION_MAX_SIZE_MESSAGE, LocalDateTime.now()
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

    @ExceptionHandler(LocationAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> departmentAlreadyExistsException(LocationAlreadyExistsException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionResponse(ExceptionConstants.LOCATION_EXIST_MESSAGE, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(LocationNameMaxSizeExceedException.class)
    public ResponseEntity<ExceptionResponse> locationNameMaxSizeException(LocationNameMaxSizeExceedException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionResponse(ExceptionConstants.LOCATION_NAME_MAX_SIZE_MESSAGE, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(LocationDescriptionMaxSizeExceedException.class)
    public ResponseEntity<ExceptionResponse> locationDescriptionMaxSizeException(LocationDescriptionMaxSizeExceedException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionResponse(ExceptionConstants.LOCATION_DESCRIPTION_MAX_SIZE_MESSAGE, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(LocationNotFoundException.class)
    public ResponseEntity<ExceptionResponse> locationNotFoundException(LocationNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionResponse(ExceptionConstants.LOCATION_NOT_FOUND_MESSAGE, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(LocationOrderNotFoundException.class)
    public ResponseEntity<ExceptionResponse> locationOrderNotFoundException(LocationOrderNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionResponse(ExceptionConstants.LOCATION_ORDER_NOT_FOUND_MESSAGE, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(CategoryNameCannotBeEmptyException.class)
    public ResponseEntity<ExceptionResponse> categoryNameCannotBeEmptyException(CategoryNameCannotBeEmptyException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionResponse(ExceptionConstants.CATEGORY_NAME_CANNOT_BE_NULL_MESSAGE, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(CategoryDescriptionCannotBeEmptyException.class)
    public ResponseEntity<ExceptionResponse> categoryDescriptionCannotBeEmptyException(CategoryDescriptionCannotBeEmptyException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionResponse(ExceptionConstants.CATEGORY_DESCRIPTION_CANNOT_BE_NULL_MESSAGE, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(LocationCityNameCannotBeEmptyException.class)
    public ResponseEntity<ExceptionResponse> locationCityNameCannotBeEmptyException(LocationCityNameCannotBeEmptyException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionResponse(ExceptionConstants.LOCATION_CITY_NAME_CANNOT_BE_NULL_MESSAGE, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(LocationCityDescriptionCannotBeEmptyException.class)
    public ResponseEntity<ExceptionResponse> locationCityDescriptionCannotBeEmptyException(LocationCityDescriptionCannotBeEmptyException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionResponse(ExceptionConstants.LOCATION_CITY_DESCRIPTION_CANNOT_BE_NULL_MESSAGE, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(LocationDepartmentNameCannotBeEmptyException.class)
    public ResponseEntity<ExceptionResponse> locationDepartmentNameCannotBeEmptyException(LocationDepartmentNameCannotBeEmptyException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionResponse(ExceptionConstants.LOCATION_DEPARTMENT_NAME_CANNOT_BE_NULL_MESSAGE, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(LocationDepartmentDescriptionCannotBeEmptyException.class)
    public ResponseEntity<ExceptionResponse> locationDepartmentDescriptionCannotBeEmptyException(LocationDepartmentDescriptionCannotBeEmptyException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionResponse(ExceptionConstants.LOCATION_DEPARTMENT_DESCRIPTION_CANNOT_BE_NULL_MESSAGE, LocalDateTime.now()
                )
        );
    }
}
