package org.example.controller;

import org.example.domain.SearchResponse;
import org.example.dto.RecipeDto;
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
@Validated
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RecipeSearchService recipeSearchService;

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDto> getRecipeById(@PathVariable @Min(value = 1, message = "Id should be a valid number with minimum value of 1") Integer id) {
        RecipeDto recipe = recipeService.findRecipeById(id);
            return ResponseEntity.ok(recipe);
    }

    @GetMapping("/search")
    public ResponseEntity<List<SearchResponse>> searchRecipes(@RequestParam String query, @RequestParam(defaultValue = "1") @Min(value = 1, message = "page number should be a valid number with minimum value of 1") int page, @RequestParam(defaultValue = "10") @Min(value = 1, message = "Page size should be a valid number with minimum value of 1") int size) {
        List<SearchResponse> recipes = recipeSearchService.searchRecipes(query, page, size);
        return ResponseEntity.ok(recipes);
    }
}
