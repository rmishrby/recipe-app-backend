package org.example.util;

import org.example.domain.Recipe;
import org.example.dto.RecipeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class RecipeMapperTest {

    private Recipe recipe;

    @BeforeEach
    public void setup() {
        recipe = new Recipe();
        recipe.setId(1);
        recipe.setName("Test Recipe");
        recipe.setIngredients(Arrays.asList("ingredient1", "ingredient2"));
        recipe.setInstructions(Arrays.asList("step1", "step2"));
        recipe.setPrepTimeMinutes(30);
        recipe.setCookTimeMinutes(45);
        recipe.setServings(4);
        recipe.setDifficulty("Medium");
        recipe.setCuisine("Italian");
        recipe.setCaloriesPerServing(350);
        recipe.setTags(Arrays.asList("dinner", "pasta"));
        recipe.setImage("image1.jpg");
        recipe.setRating(4.5);
        recipe.setReviewCount(120);
        recipe.setMealType(Arrays.asList("dinner"));
    }

    @Test
    public void whenToDTO_thenMapAllFields() {
        // when
        RecipeDto dto = RecipeMapper.toDTO(recipe);

        // then
        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(recipe.getId());
        assertThat(dto.getName()).isEqualTo(recipe.getName());
        assertThat(dto.getIngredients()).isEqualTo(recipe.getIngredients());
        assertThat(dto.getInstructions()).isEqualTo(recipe.getInstructions());
        assertThat(dto.getPrepTimeMinutes()).isEqualTo(recipe.getPrepTimeMinutes());
        assertThat(dto.getCookTimeMinutes()).isEqualTo(recipe.getCookTimeMinutes());
        assertThat(dto.getServings()).isEqualTo(recipe.getServings());
        assertThat(dto.getDifficulty()).isEqualTo(recipe.getDifficulty());
        assertThat(dto.getCuisine()).isEqualTo(recipe.getCuisine());
        assertThat(dto.getCaloriesPerServing()).isEqualTo(recipe.getCaloriesPerServing());
        assertThat(dto.getTags()).isEqualTo(recipe.getTags());
        assertThat(dto.getImage()).isEqualTo(recipe.getImage());
        assertThat(dto.getRating()).isEqualTo(recipe.getRating());
        assertThat(dto.getReviewCount()).isEqualTo(recipe.getReviewCount());
        assertThat(dto.getMealType()).isEqualTo(recipe.getMealType());
    }

    @Test
    public void whenToDTOWithNullRecipe_thenReturnNull() {
        // when
        RecipeDto dto = RecipeMapper.toDTO(null);

        // then
        assertThat(dto).isNull();
    }
} 