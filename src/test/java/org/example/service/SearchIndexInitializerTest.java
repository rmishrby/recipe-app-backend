package org.example.service;

import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SearchIndexInitializerTest {

    @Mock
    private EntityManager entityManager;

    @Spy
    @InjectMocks
    private SearchIndexInitializer searchIndexInitializer;

    @Test
    public void whenInitializeHibernateSearch_thenStartAndWaitMassIndexer() throws InterruptedException {
        SearchSession searchSession = mock(SearchSession.class);

        MassIndexer massIndexer = mock(MassIndexer.class);

        when(searchSession.massIndexer()).thenReturn(massIndexer);
        doNothing().when(massIndexer).startAndWait();

        doReturn(searchSession).when(searchIndexInitializer).getSearchSession(entityManager);

        searchIndexInitializer.initializeHibernateSearch();

        verify(searchSession).massIndexer();
        verify(massIndexer).startAndWait();
    }
    
    @Test
    public void whenInitializeHibernateSearchWithException_thenPropagateException() throws InterruptedException {
        SearchSession searchSession = mock(SearchSession.class);

        MassIndexer massIndexer = mock(MassIndexer.class);

        when(searchSession.massIndexer()).thenReturn(massIndexer);
        doThrow(new InterruptedException("Test exception")).when(massIndexer).startAndWait();

        doReturn(searchSession).when(searchIndexInitializer).getSearchSession(entityManager);

        try {
            searchIndexInitializer.initializeHibernateSearch();
            org.junit.jupiter.api.Assertions.fail("Expected InterruptedException was not thrown");
        } catch (InterruptedException e) {
            org.junit.jupiter.api.Assertions.assertEquals("Test exception", e.getMessage());
        }

        verify(searchSession).massIndexer();
        verify(massIndexer).startAndWait();
    }
} 