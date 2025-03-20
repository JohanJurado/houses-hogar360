package com.pragma.hogar360_microservice_house.infraestructure.endpoints.rest;

import com.pragma.hogar360_microservice_house.application.dtos.request.SaveCategoryRequest;
import com.pragma.hogar360_microservice_house.application.dtos.response.CategoryResponse;
import com.pragma.hogar360_microservice_house.application.dtos.response.SaveDtoResponses;
import com.pragma.hogar360_microservice_house.application.services.ICategoryService;
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
                    schema = @Schema(implementation = SaveDtoResponses.class)
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
    public ResponseEntity<SaveDtoResponses> save(@RequestBody SaveCategoryRequest saveCategoryRequest){
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
                    description = "Category or page not found",
                    content = @Content
            )
    })
    @GetMapping("/")
    public ResponseEntity<Pagination<CategoryResponse>> getCategories(
            @RequestParam(defaultValue = PaginationConstants.NAME_CATEGORY_DEFAULT_PAGINATION) String nameCategory,
            @RequestParam(defaultValue = PaginationConstants.PAGE_DEFAULT_PAGINATION) Integer page,
            @RequestParam(defaultValue = PaginationConstants.SIZE_DEFAULT_PAGINATION) Integer size,
            @RequestParam(defaultValue = PaginationConstants.ORDER_ASC_DEFAULT_PAGINATION) boolean orderAsc
    ){
        Pagination<CategoryResponse> response = categoryService.getCategories(nameCategory, page, size, orderAsc);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
