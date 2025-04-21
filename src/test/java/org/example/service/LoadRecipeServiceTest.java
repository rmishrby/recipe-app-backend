package org.example.service;

import org.example.domain.Recipe;
import org.example.domain.RecipeResponse;
import org.example.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoadRecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private LoadRecipeService loadRecipeService;

    private Recipe recipe1;
    private Recipe recipe2;
    private RecipeResponse recipeResponse;

    @BeforeEach
    public void setup() {
        // Inject the mocked RestTemplate into the service
        ReflectionTestUtils.setField(loadRecipeService, "restTemplate", restTemplate);

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

        recipeResponse = new RecipeResponse();
        recipeResponse.setRecipes(Arrays.asList(recipe1, recipe2));
        recipeResponse.setTotal(2);
        recipeResponse.setSkip(0);
        recipeResponse.setLimit(10);
    }

    @Test
    public void whenFetchAndSaveRecipes_thenSaveToRepository() {
        String url = "https://dummyjson.com/recipes?limit=0&skip=0";
        when(restTemplate.getForObject(eq(url), eq(RecipeResponse.class)))
                .thenReturn(recipeResponse);
        when(recipeRepository.saveAll(any())).thenReturn(Arrays.asList(recipe1, recipe2));

        loadRecipeService.fetchAndSaveRecipes();

        verify(recipeRepository).saveAll(recipeResponse.getRecipes());
    }

    @Test
    public void whenFetchAndSaveRecipesWithNullResponse_thenDoNotSave() {
        String url = "https://dummyjson.com/recipes?limit=0&skip=0";
        when(restTemplate.getForObject(eq(url), eq(RecipeResponse.class))).thenReturn(null);

        loadRecipeService.fetchAndSaveRecipes();

        verify(recipeRepository, never()).saveAll(any());
    }

    @Test
    public void whenFetchAndSaveRecipesWithNullRecipes_thenDoNotSave() {
        String url = "https://dummyjson.com/recipes?limit=0&skip=0";
        RecipeResponse emptyResponse = new RecipeResponse();
        emptyResponse.setRecipes(null);
        when(restTemplate.getForObject(eq(url), eq(RecipeResponse.class))).thenReturn(emptyResponse);

        loadRecipeService.fetchAndSaveRecipes();

        verify(recipeRepository, never()).saveAll(any());
    }
} 