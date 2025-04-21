package org.example.service;

import javax.annotation.PostConstruct;
import org.example.domain.Recipe;
import org.example.domain.RecipeResponse;
import org.example.repository.RecipeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class LoadRecipeService {
    private static final Logger logger = LoggerFactory.getLogger(LoadRecipeService.class);

    @Autowired
    private RecipeRepository recipeRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    @PostConstruct
    public void fetchAndSaveRecipes() {
        logger.info("Starting to fetch and save recipes");
        String url = "https://dummyjson.com/recipes?limit=0&skip=0";
        try {
            RecipeResponse response = restTemplate.getForObject(url, RecipeResponse.class);
            if (response != null && response.getRecipes() != null) {
                List<Recipe> recipes = response.getRecipes();
                logger.debug("Fetched {} recipes from external API", recipes.size());
                recipeRepository.saveAll(recipes);
                logger.info("Successfully saved {} recipes to database", recipes.size());
            } else {
                logger.warn("No recipes received from external API");
            }
        } catch (Exception e) {
            logger.error("Error while fetching and saving recipes: {}", e.getMessage(), e);
            throw e;
        }
    }
}
