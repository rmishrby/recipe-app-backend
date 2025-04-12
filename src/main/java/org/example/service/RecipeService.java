// Java::RecipeService.java::src/main/java/org/example/service/RecipeService.java
package org.example.service;

import org.example.domain.Recipe;
import org.example.dto.RecipeDto;
import org.example.repository.RecipeRepository;
import org.example.util.RecipeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public RecipeDto findRecipeById(Integer id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        return recipe.map(RecipeMapper::toDTO).orElse(null);
    }
}
