package com.pragma.hogar360_microservice_house.category.infraestructure.endpoints.rest;

import com.pragma.hogar360_microservice_house.category.application.dtos.request.SaveCategoryRequest;
import com.pragma.hogar360_microservice_house.category.application.dtos.response.SaveCategoryResponse;
import com.pragma.hogar360_microservice_house.category.application.services.ICategoryService;
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
@RequestMapping("/api/category")
@Tag(name = "Categories", description = "API to manage categories")
public class CategoryController {

    private final ICategoryService categoryService;

    @Operation(summary = "Save Category", description = "Create a new category")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Category created",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = SaveCategoryResponse.class)
                )
            }
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Category invalid",
            content = @Content
        )
    })
    @PostMapping("/")
    public ResponseEntity<SaveCategoryResponse> save(@RequestBody SaveCategoryRequest saveCategoryRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(saveCategoryRequest));
    }
}
