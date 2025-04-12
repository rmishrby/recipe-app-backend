package org.example.service;

import org.example.domain.Recipe;
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
    public List<Recipe> searchRecipes(String keyword) {
        SearchSession searchSession = Search.session(entityManager);

        return searchSession.search(Recipe.class)
                .where(f -> f.bool(b -> {
                    b.should(f.match().fields("name").matching(keyword));
                    b.should(f.match().fields("cuisine").matching(keyword));
                }))
                .fetchHits(20); // limit to top 20 results
    }
}

