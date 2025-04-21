package org.example.service;

import org.example.domain.Recipe;
import org.example.domain.SearchResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

@ExtendWith(MockitoExtension.class)
public class RecipeSearchServiceTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private RecipeSearchService recipeSearchService;

    @Test
    public void whenSearchRecipes_thenReturnSearchResponses() {
        String keyword = "Italian";
        int pageNumber = 1;
        int pageSize = 10;

        List<SearchResponse> mockResults = getSearchResponses();

        RecipeSearchService spyService = spy(recipeSearchService);

        doReturn(mockResults).when(spyService).searchRecipes(anyString(), anyInt(), anyInt());

        List<SearchResponse> results = spyService.searchRecipes(keyword, pageNumber, pageSize);

        assertThat(results).hasSize(2);
        assertThat(results.get(0).getId()).isEqualTo(45);
        assertThat(results.get(0).getName()).isEqualTo("Italian Margherita Pizza");
        assertThat(results.get(0).getCuisine()).isEqualTo("Italian");
        assertThat(results.get(1).getId()).isEqualTo(41);
        assertThat(results.get(1).getName()).isEqualTo("Caprese Bruschetta");
        assertThat(results.get(1).getCuisine()).isEqualTo("Italian");
    }

    private static List<SearchResponse> getSearchResponses() {
        Recipe recipe1 = new Recipe();
        recipe1.setId(45);
        recipe1.setName("Italian Margherita Pizza");
        recipe1.setCuisine("Italian");

        Recipe recipe2 = new Recipe();
        recipe2.setId(41);
        recipe2.setName("Caprese Bruschetta");
        recipe2.setCuisine("Italian");

        SearchResponse response1 = new SearchResponse(recipe1.getId(), recipe1.getName(), recipe1.getCuisine());
        SearchResponse response2 = new SearchResponse(recipe2.getId(), recipe2.getName(), recipe2.getCuisine());

        List<SearchResponse> mockResults = Arrays.asList(response1, response2);
        return mockResults;
    }
} 