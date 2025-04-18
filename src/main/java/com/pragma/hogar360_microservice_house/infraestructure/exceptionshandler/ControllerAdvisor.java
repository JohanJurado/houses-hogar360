package com.pragma.hogar360_microservice_house.infraestructure.exceptionshandler;

import com.pragma.hogar360_microservice_house.domain.exceptions.*;
import com.pragma.hogar360_microservice_house.infraestructure.utils.constants.ExceptionConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

import static com.pragma.hogar360_microservice_house.infraestructure.utils.constants.ExceptionConstants.NOT_PERMISSIONS_MESSAGE;

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

    @ExceptionHandler(CityNameCannotBeEmptyException.class)
    public ResponseEntity<ExceptionResponse> locationCityNameCannotBeEmptyException(CityNameCannotBeEmptyException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionResponse(ExceptionConstants.LOCATION_CITY_NAME_CANNOT_BE_NULL_MESSAGE, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(CityDescriptionCannotBeEmptyException.class)
    public ResponseEntity<ExceptionResponse> locationCityDescriptionCannotBeEmptyException(CityDescriptionCannotBeEmptyException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionResponse(ExceptionConstants.LOCATION_CITY_DESCRIPTION_CANNOT_BE_NULL_MESSAGE, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(DepartmentNameCannotBeEmptyException.class)
    public ResponseEntity<ExceptionResponse> locationDepartmentNameCannotBeEmptyException(DepartmentNameCannotBeEmptyException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionResponse(ExceptionConstants.LOCATION_DEPARTMENT_NAME_CANNOT_BE_NULL_MESSAGE, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(DepartmentDescriptionCannotBeEmptyException.class)
    public ResponseEntity<ExceptionResponse> locationDepartmentDescriptionCannotBeEmptyException(DepartmentDescriptionCannotBeEmptyException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionResponse(ExceptionConstants.LOCATION_DEPARTMENT_DESCRIPTION_CANNOT_BE_NULL_MESSAGE, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(HouseActivePublicationDateCannotBeEmptyException.class)
    public ResponseEntity<ExceptionResponse> houseActivePublicationDateCannotBeEmptyException(HouseActivePublicationDateCannotBeEmptyException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionResponse(ExceptionConstants.HOUSE_ACTIVE_PUBLICATION_DATE_CANNOT_BE_NULL_MESSAGE, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(HouseBathroomCountCannotBeEmptyException.class)
    public ResponseEntity<ExceptionResponse> houseBathroomCountCannotBeEmptyException(HouseBathroomCountCannotBeEmptyException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionResponse(ExceptionConstants.HOUSE_BATHROOM_COUNT_CANNOT_BE_NULL_MESSAGE, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(HouseBedroomCountCannotBeEmptyException.class)
    public ResponseEntity<ExceptionResponse> houseBedroomCountCannotBeEmptyException(HouseBedroomCountCannotBeEmptyException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionResponse(ExceptionConstants.HOUSE_BEDROOM_COUNT_CANNOT_BE_NULL_MESSAGE, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(HouseCategoryCannotBeEmptyException.class)
    public ResponseEntity<ExceptionResponse> houseCategoryCannotBeEmptyException(HouseCategoryCannotBeEmptyException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionResponse(ExceptionConstants.HOUSE_CATEGORY_CANNOT_BE_NULL_MESSAGE, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(HouseDescriptionCannotBeEmptyException.class)
    public ResponseEntity<ExceptionResponse> houseDescriptionCannotBeEmptyException(HouseDescriptionCannotBeEmptyException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionResponse(ExceptionConstants.HOUSE_DESCRIPTION_CANNOT_BE_NULL_MESSAGE, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(HouseLocationCannotBeEmptyException.class)
    public ResponseEntity<ExceptionResponse> houseLocationCannotBeEmptyException(HouseLocationCannotBeEmptyException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionResponse(ExceptionConstants.HOUSE_LOCATION_CANNOT_BE_NULL_MESSAGE, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(HouseNameCannotBeEmptyException.class)
    public ResponseEntity<ExceptionResponse> houseNameCannotBeEmptyException(HouseNameCannotBeEmptyException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionResponse(ExceptionConstants.HOUSE_NAME_CANNOT_BE_NULL_MESSAGE, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(HousePriceCannotBeEmptyException.class)
    public ResponseEntity<ExceptionResponse> housePriceCannotBeEmptyException(HousePriceCannotBeEmptyException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionResponse(ExceptionConstants.HOUSE_PRICE_CANNOT_BE_NULL_MESSAGE, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(HouseOrderNotFoundException.class)
    public ResponseEntity<ExceptionResponse> houseOrderNotFoundException(HouseOrderNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionResponse(ExceptionConstants.HOUSE_ORDER_NOT_FOUND_MESSAGE, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(LocationNeighborhoodCannotBeEmptyException.class)
    public ResponseEntity<ExceptionResponse> locationNeighborhoodCannotBeEmptyException(LocationNeighborhoodCannotBeEmptyException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionResponse(ExceptionConstants.LOCATION_NEIGHBORHOOD_CANNOT_BE_NULL_MESSAGE, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(LocationNeighborhoodMaxSizeExceedException.class)
    public ResponseEntity<ExceptionResponse> locationNeighborhoodMaxSizeExceedException(LocationNeighborhoodMaxSizeExceedException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionResponse(ExceptionConstants.LOCATION_NEIGHBORHOOD_MAX_SIZE_MESSAGE, LocalDateTime.now()
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

    @ExceptionHandler(HouseLimitActivePublicationDateExceedException.class)
    public ResponseEntity<ExceptionResponse> houseLimitActivePublicationDateExceedException(HouseLimitActivePublicationDateExceedException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionResponse(ExceptionConstants.HOUSE_LIMIT_ACTIVE_PUBLICATION_DATE_MESSAGE, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionResponse> handleForbidden() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ExceptionResponse(NOT_PERMISSIONS_MESSAGE, LocalDateTime.now()));
    }
}
