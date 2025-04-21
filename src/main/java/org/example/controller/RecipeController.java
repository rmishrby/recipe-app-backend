package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.domain.SearchResponse;
import org.example.dto.ErrorResponse;
import org.example.dto.RecipeDto;
import org.example.dto.RecipeResponseDto;
import org.example.service.RecipeSearchService;
import org.example.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api/recipes")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RecipeSearchService recipeSearchService;

    @Operation(summary = "Get a recipe by ID", description = "Returns a single recipe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = RecipeDto.class))),
            @ApiResponse(responseCode = "404", description = "Recipe not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<RecipeDto> getRecipeById(@PathVariable @Min(value = 1, message = "Id should be a valid number with minimum value of 1") Integer id) {
        RecipeDto recipe = recipeService.findRecipeById(id);
            return ResponseEntity.ok(recipe);
    }

    @Operation(summary = "Get all recipes with pagination", description = "Returns a paginated list of recipes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = RecipeResponseDto.class)))})
    @GetMapping("/findAll")
    public ResponseEntity<RecipeResponseDto> getAllRecipes(
            @RequestParam(defaultValue = "0") @Min(value = 0, message = "Page number should be a non-negative number") int page,
            @RequestParam(defaultValue = "10") @Min(value = 1, message = "Page size should be a positive number") int size) {

        RecipeResponseDto response = recipeService.findAllRecipes(page, size);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Search recipes", description = "Search recipes based on the provided query")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "400", description = "Validation error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/search")
    public ResponseEntity<List<SearchResponse>> searchRecipes(@RequestParam String query, @RequestParam(defaultValue = "1") @Min(value = 1, message = "page number should be a valid number with minimum value of 1") int page, @RequestParam(defaultValue = "10") @Min(value = 1, message = "Page size should be a valid number with minimum value of 1") int size) {
        List<SearchResponse> recipes = recipeSearchService.searchRecipes(query, page, size);
        return ResponseEntity.ok(recipes);
    }
}
