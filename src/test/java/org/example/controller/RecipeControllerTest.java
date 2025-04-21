package org.example.controller;

import org.example.domain.SearchResponse;
import org.example.dto.RecipeDto;
import org.example.dto.RecipeResponseDto;
import org.example.service.RecipeSearchService;
import org.example.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecipeControllerTest {

    @Mock
    private RecipeService recipeService;

    @Mock
    private RecipeSearchService recipeSearchService;

    @InjectMocks
    private RecipeController recipeController;

    private RecipeDto recipeDto;
    private RecipeResponseDto recipeResponseDto;
    private List<SearchResponse> searchResponses;

    @BeforeEach
    public void setup() {
        recipeDto = new RecipeDto();
        recipeDto.setId(1);
        recipeDto.setName("Test Recipe");
        recipeDto.setIngredients(Arrays.asList("ingredient1", "ingredient2"));
        recipeDto.setInstructions(Arrays.asList("step1", "step2"));
        recipeDto.setCuisine("Italian");
        recipeDto.setPrepTimeMinutes(30);
        recipeDto.setCookTimeMinutes(45);
        recipeDto.setServings(4);
        recipeDto.setDifficulty("Medium");
        recipeDto.setCaloriesPerServing(350);
        recipeDto.setTags(Arrays.asList("dinner", "pasta"));
        recipeDto.setImage("image1.jpg");
        recipeDto.setRating(4.5);
        recipeDto.setReviewCount(120);
        recipeDto.setMealType(Arrays.asList("dinner"));

        recipeResponseDto = new RecipeResponseDto();
        recipeResponseDto.setRecipes(Arrays.asList(recipeDto));
        recipeResponseDto.setTotal(1);
        recipeResponseDto.setSkip(0);
        recipeResponseDto.setLimit(10);

        SearchResponse searchResponse = new SearchResponse();
        searchResponse.setId(1);
        searchResponse.setName("Test Recipe");
        searchResponse.setCuisine("Italian");
        searchResponses = Arrays.asList(searchResponse);
    }

    @Test
    public void whenGetRecipeById_thenReturnRecipe() {
        when(recipeService.findRecipeById(anyInt())).thenReturn(recipeDto);

        ResponseEntity<RecipeDto> response = recipeController.getRecipeById(1);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(recipeDto, response.getBody());
    }

    @Test
    public void whenGetAllRecipes_thenReturnRecipeList() {
        when(recipeService.findAllRecipes(anyInt(), anyInt())).thenReturn(recipeResponseDto);

        ResponseEntity<RecipeResponseDto> response = recipeController.getAllRecipes(0, 10);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(recipeResponseDto, response.getBody());
    }

    @Test
    public void whenSearchRecipes_thenReturnSearchResults() {
        when(recipeSearchService.searchRecipes(anyString(), anyInt(), anyInt())).thenReturn(searchResponses);

        ResponseEntity<List<SearchResponse>> response = recipeController.searchRecipes("test", 1, 10);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(searchResponses, response.getBody());
    }
} 