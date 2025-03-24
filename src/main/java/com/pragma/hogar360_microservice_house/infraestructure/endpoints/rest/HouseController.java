package com.pragma.hogar360_microservice_house.infraestructure.endpoints.rest;

import com.pragma.hogar360_microservice_house.application.dtos.request.SaveHouseRequest;
import com.pragma.hogar360_microservice_house.application.dtos.response.SaveDtoResponses;
import com.pragma.hogar360_microservice_house.application.services.IHouseService;
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
@RequestMapping("/api/house")
@Tag(name = "Houses", description = "API to manage Houses")
public class HouseController {

    private final IHouseService houseService;

    @Operation(summary = "Publish House", description = "Create a new house")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "House published",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SaveDtoResponses.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "House invalid",
                    content = @Content
            )
    })
    @PostMapping("/")
    public ResponseEntity<SaveDtoResponses> save(@RequestBody SaveHouseRequest saveHouseRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(houseService.publish(saveHouseRequest));
    }
}
