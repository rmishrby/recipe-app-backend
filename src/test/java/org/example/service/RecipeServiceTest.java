package org.example.service;

import org.example.domain.Recipe;
import org.example.dto.RecipeDto;
import org.example.dto.RecipeResponseDto;
import org.example.exception.RecipeNotFound;
import org.example.repository.RecipeRepository;
import org.example.util.RecipeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private RecipeService recipeService;

    private Recipe recipe1;
    private Recipe recipe2;

    @BeforeEach
    public void setup() {
        recipe1 = new Recipe();
        recipe1.setId(1);
        recipe1.setName("Recipe 1");
        recipe1.setIngredients(Arrays.asList("ingredient1", "ingredient2"));
        recipe1.setInstructions(Arrays.asList("step1", "step2"));
        recipe1.setCuisine("Italian");
        recipe1.setPrepTimeMinutes(30);
        recipe1.setCookTimeMinutes(45);
        recipe1.setServings(4);
        recipe1.setDifficulty("Medium");
        recipe1.setCaloriesPerServing(350);
        recipe1.setTags(Arrays.asList("dinner", "pasta"));
        recipe1.setImage("image1.jpg");
        recipe1.setRating(4.5);
        recipe1.setReviewCount(120);
        recipe1.setMealType(Arrays.asList("dinner"));

        recipe2 = new Recipe();
        recipe2.setId(2);
        recipe2.setName("Recipe 2");
        recipe2.setIngredients(Arrays.asList("ingredient3", "ingredient4"));
        recipe2.setInstructions(Arrays.asList("step3", "step4"));
        recipe2.setCuisine("Mexican");
        recipe2.setPrepTimeMinutes(20);
        recipe2.setCookTimeMinutes(30);
        recipe2.setServings(2);
        recipe2.setDifficulty("Easy");
        recipe2.setCaloriesPerServing(250);
        recipe2.setTags(Arrays.asList("lunch", "quick"));
        recipe2.setImage("image2.jpg");
        recipe2.setRating(4.0);
        recipe2.setReviewCount(80);
        recipe2.setMealType(Arrays.asList("lunch"));
    }

    @Test
    public void whenFindRecipeById_thenReturnRecipeDto() {
        when(recipeRepository.findById(1)).thenReturn(Optional.of(recipe1));

        RecipeDto result = recipeService.findRecipeById(1);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getName()).isEqualTo("Recipe 1");
        assertThat(result.getCuisine()).isEqualTo("Italian");
    }

    @Test
    public void whenFindRecipeByIdNotFound_thenThrowException() {
        when(recipeRepository.findById(999)).thenReturn(Optional.empty());

        assertThrows(RecipeNotFound.class, () -> recipeService.findRecipeById(999));
    }

    @Test
    public void whenFindAllRecipes_thenReturnRecipeResponseDto() {
        List<Recipe> recipes = Arrays.asList(recipe1, recipe2);
        Page<Recipe> recipePage = new PageImpl<>(recipes, PageRequest.of(0, 10), 2);
        when(recipeRepository.findAll(any(Pageable.class))).thenReturn(recipePage);

        RecipeResponseDto result = recipeService.findAllRecipes(0, 10);

        assertThat(result).isNotNull();
        assertThat(result.getRecipes()).hasSize(2);
        assertThat(result.getTotal()).isEqualTo(2);
        assertThat(result.getSkip()).isEqualTo(0);
        assertThat(result.getLimit()).isEqualTo(10);
    }
} 