package org.example.controller;

import org.example.domain.Recipe;
import org.example.domain.SearchResponse;
import org.example.service.RecipeSearchService;
import org.example.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RecipeSearchService recipeSearchService;

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Integer id) {
        Recipe recipe = recipeService.findRecipeById(id);
        if (recipe != null) {
            return ResponseEntity.ok(recipe);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<SearchResponse>> searchRecipes(@RequestParam String query, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        List<SearchResponse> recipes = recipeSearchService.searchRecipes(query, page, size);
        return ResponseEntity.ok(recipes);
    }
}
