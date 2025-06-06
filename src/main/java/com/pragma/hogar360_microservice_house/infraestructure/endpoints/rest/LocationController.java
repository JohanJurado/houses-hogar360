package com.pragma.hogar360_microservice_house.infraestructure.endpoints.rest;

import com.pragma.hogar360_microservice_house.application.dtos.request.SaveLocationRequest;
import com.pragma.hogar360_microservice_house.application.dtos.response.LocationResponse;
import com.pragma.hogar360_microservice_house.application.dtos.response.SaveDtoResponses;
import com.pragma.hogar360_microservice_house.application.services.ILocationService;
import com.pragma.hogar360_microservice_house.domain.util.pagination.Pagination;
import com.pragma.hogar360_microservice_house.domain.util.pagination.PaginationConstants;
import com.pragma.hogar360_microservice_house.infraestructure.entities.CityEntity;
import com.pragma.hogar360_microservice_house.infraestructure.entities.DepartmentEntity;
import com.pragma.hogar360_microservice_house.infraestructure.entities.LocationEntity;
import com.pragma.hogar360_microservice_house.infraestructure.repositories.mysql.ICityRepository;
import com.pragma.hogar360_microservice_house.infraestructure.repositories.mysql.IDepartmentRepository;
import com.pragma.hogar360_microservice_house.infraestructure.repositories.mysql.ILocationRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;
import java.util.Objects;

import static com.pragma.hogar360_microservice_house.infraestructure.utils.constants.InfrastructureConstants.HAS_ROLE_ADMIN;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/location")
@Tag(name = "Locations", description = "API to manage locations")
public class LocationController {

    private final ILocationService locationService;

    @Operation(summary = "Save Locations", description = "Create a new location")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Location created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SaveDtoResponses.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Location invalid",
                    content = @Content
            )
    })
    @PostMapping("/")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseEntity<SaveDtoResponses> save(@RequestBody SaveLocationRequest saveLocationRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(locationService.save(saveLocationRequest));
    }

    @Operation(summary = "Get Locations", description = "Show locations")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Locations found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Book.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Location or page not found",
                    content = @Content
            )
    })
    @GetMapping("/")
    public ResponseEntity<Pagination<LocationResponse>> getLocations(
            @RequestParam(defaultValue = PaginationConstants.NAME_LOCATION_DEFAULT_PAGINATION) String nameLocation,
            @RequestParam(defaultValue = PaginationConstants.PAGE_DEFAULT_PAGINATION) Integer page,
            @RequestParam(defaultValue = PaginationConstants.SIZE_DEFAULT_PAGINATION) Integer size,
            @RequestParam(defaultValue = PaginationConstants.ORDER_BY_LOCATION_DEFAULT_PAGINATION) String orderBy,
            @RequestParam(defaultValue = PaginationConstants.ORDER_ASC_DEFAULT_PAGINATION) boolean orderAsc
    ){
        Pagination<LocationResponse> response = locationService.getLocations(nameLocation, page, size, orderBy, orderAsc);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // endpoints adicionales para el front (no hacen parte del reto backend)

    private final IDepartmentRepository departmentRepository;
    private final ICityRepository cityRepository;
    private final ILocationRepository locationRepository;

    @GetMapping("/get-departments")
    public ResponseEntity<List<DepartmentEntity>> getDepartments(@RequestParam(defaultValue = "") String nameDepartment){
        List<DepartmentEntity> response;
        if (Objects.equals(nameDepartment, "")){
             response = departmentRepository.findAll();
        } else {
            response = departmentRepository.findByMatches(nameDepartment.toUpperCase());
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/get-cities")
    public ResponseEntity<List<CityEntity>> getCities(
            @RequestParam(defaultValue = "") String nameCity,
            @RequestParam(defaultValue = "") Long idDepartment
    ){
        List<CityEntity> response = cityRepository.findByMatches(nameCity.toUpperCase(), idDepartment);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/get-neighborhoods")
    public ResponseEntity<List<LocationEntity>> getNeighborhoods(
            @RequestParam(defaultValue = "") String nameNeighborhood,
            @RequestParam(defaultValue = "") Long idCity,
            @RequestParam(defaultValue = "") Long idDepartment
    ){
        List<LocationEntity> response = locationRepository.findByMatches(nameNeighborhood.toUpperCase(), idCity, idDepartment);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
