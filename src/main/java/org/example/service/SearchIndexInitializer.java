package org.example.service;

import javax.persistence.PersistenceContext;
import org.hibernate.search.mapper.orm.Search;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;


@Component
public class SearchIndexInitializer {

    @PersistenceContext
    private EntityManager entityManager;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initializeHibernateSearch() throws InterruptedException {
        Search.session(entityManager)
                .massIndexer()
                .startAndWait();
    }
}

