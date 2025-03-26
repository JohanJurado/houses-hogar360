package com.pragma.hogar360_microservice_house.infraestructure.endpoints.rest;

import com.pragma.hogar360_microservice_house.application.dtos.request.SaveHouseRequest;
import com.pragma.hogar360_microservice_house.application.dtos.response.HouseResponse;
import com.pragma.hogar360_microservice_house.application.dtos.response.SaveDtoResponses;
import com.pragma.hogar360_microservice_house.application.services.IHouseService;
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

    @Operation(summary = "Get Houses", description = "Show Houses")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Houses found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Book.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "House or page not found",
                    content = @Content
            )
    })
    @GetMapping("/")
    public ResponseEntity<Pagination<HouseResponse>> getHouses(
            @RequestParam(required = false) String nameCity,
            @RequestParam(required = false) String nameDepartment,
            @RequestParam(required = false) String nameCategory,
            @RequestParam(required = false) Long bedroomCount,
            @RequestParam(required = false) Long bathroomCount,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(defaultValue = PaginationConstants.PAGE_DEFAULT_PAGINATION) Integer page,
            @RequestParam(defaultValue = PaginationConstants.SIZE_DEFAULT_PAGINATION) Integer size,
            @RequestParam(defaultValue = PaginationConstants.ORDER_BY_HOUSE_DEFAULT_PAGINATION) String orderBy,
            @RequestParam(defaultValue = PaginationConstants.ORDER_ASC_DEFAULT_PAGINATION) boolean orderAsc
    ){
        Pagination<HouseResponse> response = houseService.getHouses(
                nameCity, nameDepartment, nameCategory, bedroomCount, bathroomCount, minPrice, maxPrice, page, size, orderBy, orderAsc
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
