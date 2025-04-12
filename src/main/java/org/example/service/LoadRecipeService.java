package org.example.service;

import javax.annotation.PostConstruct;
import org.example.domain.Recipe;
import org.example.domain.RecipeResponse;
import org.example.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class LoadRecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    @PostConstruct
    public void fetchAndSaveRecipes() {
        String url = "https://dummyjson.com/recipes?limit=0&skip=0";
        RecipeResponse response = restTemplate.getForObject(url, RecipeResponse.class);
        if (response != null && response.getRecipes() != null) {
            List<Recipe> recipes = response.getRecipes();
            recipeRepository.saveAll(recipes);
        }
    }
}
