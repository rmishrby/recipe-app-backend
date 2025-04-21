package org.example.repository;

import org.example.domain.Recipe;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RecipeRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RecipeRepository recipeRepository;

    @Test
    public void whenFindById_thenReturnRecipe() {
        Recipe recipe = new Recipe();
        recipe.setId(1);
        recipe.setName("Test Recipe");
        recipe.setIngredients(Arrays.asList("ingredient1", "ingredient2"));
        recipe.setInstructions(Arrays.asList("step1", "step2"));
        recipe.setCuisine("Italian");
        
        entityManager.persist(recipe);
        entityManager.flush();

        Optional<Recipe> found = recipeRepository.findById(1);

        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Test Recipe");
        assertThat(found.get().getCuisine()).isEqualTo("Italian");
    }

    @Test
    public void whenFindAll_thenReturnAllRecipes() {
        Recipe recipe1 = new Recipe();
        recipe1.setId(1);
        recipe1.setName("Recipe 1");
        recipe1.setCuisine("Italian");
        
        Recipe recipe2 = new Recipe();
        recipe2.setId(2);
        recipe2.setName("Recipe 2");
        recipe2.setCuisine("Mexican");
        
        entityManager.persist(recipe1);
        entityManager.persist(recipe2);
        entityManager.flush();

        List<Recipe> recipes = recipeRepository.findAll();

        assertThat(recipes).hasSize(2);
        assertThat(recipes).extracting(Recipe::getName).containsExactlyInAnyOrder("Recipe 1", "Recipe 2");
    }
} 