// Java::RecipeService.java::src/main/java/org/example/service/RecipeService.java
package org.example.service;

import org.example.domain.Recipe;
import org.example.domain.RecipeResponse;
import org.example.dto.RecipeDto;
import org.example.dto.RecipeResponseDto;
import org.example.exception.RecipeNotFound;
import org.example.repository.RecipeRepository;
import org.example.util.RecipeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    private static final Logger logger = LoggerFactory.getLogger(RecipeService.class);

    @Autowired
    private RecipeRepository recipeRepository;

    public RecipeDto findRecipeById(Integer id) {
        logger.debug("Finding recipe by id: {}", id);
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (recipe.isEmpty()) {
            logger.warn("Recipe not found with id: {}", id);
            throw new RecipeNotFound(id);
        }
        logger.info("Recipe found with id: {}", id);
        return RecipeMapper.toDTO(recipe.get());
    }

    public RecipeResponseDto findAllRecipes(int page, int size) {
        logger.debug("Finding all recipes with page: {} and size: {}", page, size);
        Pageable pageable = PageRequest.of(page, size);
        Page<Recipe> recipePage = recipeRepository.findAll(pageable);

        List<RecipeDto> recipeDtos = recipePage.getContent().stream()
                .map(RecipeMapper::toDTO)
                .toList();

        logger.info("Found {} recipes", recipePage.getTotalElements());
        return new RecipeResponseDto(recipeDtos, (int) recipePage.getTotalElements(), page * size, size);
    }
}
