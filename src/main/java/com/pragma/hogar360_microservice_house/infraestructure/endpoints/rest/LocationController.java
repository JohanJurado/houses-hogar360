package com.pragma.hogar360_microservice_house.infraestructure.endpoints.rest;

import com.pragma.hogar360_microservice_house.application.dtos.request.SaveLocationRequest;
import com.pragma.hogar360_microservice_house.application.dtos.response.LocationResponse;
import com.pragma.hogar360_microservice_house.application.dtos.response.SaveDtoResponses;
import com.pragma.hogar360_microservice_house.application.services.ILocationService;
import com.pragma.hogar360_microservice_house.domain.util.pagination.Pagination;
import com.pragma.hogar360_microservice_house.domain.util.pagination.PaginationConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/location")
@Tag(name = "Locations", description = "API to save locations")
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
            @RequestParam(defaultValue = PaginationConstants.ORDER_BY_DEFAULT_PAGINATION) String orderBy,
            @RequestParam(defaultValue = PaginationConstants.ORDER_ASC_DEFAULT_PAGINATION) boolean orderAsc
    ){
        Pagination<LocationResponse> response = locationService.getLocations(nameLocation, page, size, orderBy, orderAsc);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
