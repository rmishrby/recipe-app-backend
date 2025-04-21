package org.example.service;

import org.example.domain.Recipe;
import org.example.domain.SearchResponse;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class RecipeSearchService {
    private static final Logger logger = LoggerFactory.getLogger(RecipeSearchService.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<SearchResponse> searchRecipes(String keyword, int pageNumber, int pageSize) {
        logger.debug("Searching recipes with keyword: {}, page: {}, size: {}", keyword, pageNumber, pageSize);
        SearchSession searchSession = Search.session(entityManager);

        int offset = (pageNumber - 1) * pageSize;

        List<SearchResponse> results = searchSession.search(Recipe.class)
                .where(f -> f.bool(b -> {
                    b.should(f.wildcard().fields("name").matching("*" + keyword + "*"));
                    b.should(f.wildcard().fields("cuisine").matching("*" + keyword + "*"));
                }))
                .fetchHits(offset, pageSize)
                .stream()
                .map(recipe -> new SearchResponse(recipe.getId(), recipe.getName(), recipe.getCuisine()))
                .toList();

        logger.info("Found {} recipes matching search criteria", results.size());
        return results;
    }
}

