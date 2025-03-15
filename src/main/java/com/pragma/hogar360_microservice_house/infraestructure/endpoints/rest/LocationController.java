package com.pragma.hogar360_microservice_house.infraestructure.endpoints.rest;

import com.pragma.hogar360_microservice_house.application.dtos.request.SaveCategoryRequest;
import com.pragma.hogar360_microservice_house.application.dtos.request.SaveLocationRequest;
import com.pragma.hogar360_microservice_house.application.dtos.response.SaveDtoResponses;
import com.pragma.hogar360_microservice_house.application.services.ILocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
