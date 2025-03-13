package com.pragma.hogar360_microservice_house.category.infraestructure.endpoints.rest;

import com.pragma.hogar360_microservice_house.category.application.dtos.request.SaveCategoryRequest;
import com.pragma.hogar360_microservice_house.category.application.dtos.response.CategoryResponse;
import com.pragma.hogar360_microservice_house.category.application.dtos.response.SaveCategoryResponse;
import com.pragma.hogar360_microservice_house.category.application.services.ICategoryService;
import com.pragma.hogar360_microservice_house.commons.configurations.utils.Pagination.Pagination;
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
import java.util.List;

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

    @Operation(summary = "Get Categories", description = "Show categories")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Categories found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Book.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Category not found",
                    content = @Content
            )
    })
    @GetMapping("/")
    public ResponseEntity<Pagination<CategoryResponse>> save(
            @RequestParam(defaultValue = "") String nameCategory,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "true") boolean orderAsc
    ){
        return ResponseEntity.status(HttpStatus.FOUND).body(categoryService.getCategories(nameCategory, page, size, orderAsc));
    }
}
