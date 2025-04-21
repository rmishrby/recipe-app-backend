package org.example.util;

import org.example.domain.Recipe;
import org.example.dto.RecipeDto;

public class RecipeMapper {

    public static RecipeDto toDTO(Recipe recipe) {
        if (recipe == null) {
            return null;
        }

        return new RecipeDto(
                recipe.getId(),
                recipe.getName(),
                recipe.getIngredients(),
                recipe.getInstructions(),
                recipe.getPrepTimeMinutes(),
                recipe.getCookTimeMinutes(),
                recipe.getServings(),
                recipe.getDifficulty(),
                recipe.getCuisine(),
                recipe.getCaloriesPerServing(),
                recipe.getTags(),
                recipe.getImage(),
                recipe.getRating(),
                recipe.getReviewCount(),
                recipe.getMealType()
        );
    }
}

