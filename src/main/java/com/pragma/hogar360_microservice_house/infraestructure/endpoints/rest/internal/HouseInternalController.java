package com.pragma.hogar360_microservice_house.infraestructure.endpoints.rest.internal;

import com.pragma.hogar360_microservice_house.infraestructure.repositories.mysql.IHouseRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.pragma.hogar360_microservice_house.infraestructure.utils.constants.InfrastructureConstants.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/internal/house")
@Tag(name = "Houses", description = "API to manage internal endpoints Houses")
public class HouseInternalController {

    private final IHouseRepository houseRepository;

    @Operation(summary = "Validate House", description = "Validate house by id seller")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "House validated"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "House invalid"
            )
    })
    @GetMapping("/validate-house/{emailSeller}/{idHouse}")
    @PreAuthorize(HAS_ROLE_MICROSERVICE)
    public boolean validateHouseByEmailSeller(
            @PathVariable String emailSeller,
            @PathVariable Long idHouse
    ){
        return houseRepository.existsByIdAndEmailSeller(idHouse, emailSeller);
    }
}
