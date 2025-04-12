package org.example.service;

import org.example.domain.Recipe;
import org.example.domain.SearchResponse;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class RecipeSearchService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<SearchResponse> searchRecipes(String keyword, int pageNumber, int pageSize) {
        SearchSession searchSession = Search.session(entityManager);

        int offset = (pageNumber - 1) * pageSize;

        return searchSession.search(Recipe.class)
                .where(f -> f.bool(b -> {
                    b.should(f.wildcard().fields("name").matching("*" + keyword + "*"));
                    b.should(f.wildcard().fields("cuisine").matching("*" + keyword + "*"));
                }))
                .fetchHits(offset, pageSize)
                .stream()
                .map(recipe -> new SearchResponse(recipe.getId(), recipe.getName(), recipe.getCuisine()))
                .toList();
    }
}

