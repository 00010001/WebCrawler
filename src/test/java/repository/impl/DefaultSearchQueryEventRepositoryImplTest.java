package repository.impl;

import model.SearchQuery;
import model.SearchQueryEvent;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class DefaultSearchQueryEventRepositoryImplTest {

    private DefaultSearchQueryEventRepositoryImpl defaultSearchQueryEventRepository;
    private SearchQuery searchQuery;
    private SearchQueryEvent searchQueryEvent;

    @Before
    public void setup() {
        defaultSearchQueryEventRepository = new DefaultSearchQueryEventRepositoryImpl();

        List<String> keywords = new ArrayList<>();
        keywords.add("testKeyword1");
        keywords.add("testKeyword2");
        keywords.add("testKeyword3");
        searchQuery = new SearchQuery();
        searchQuery.setKeywords(keywords);
        searchQuery.setLocation("testLocation");

        searchQueryEvent = new SearchQueryEvent();
        searchQueryEvent.setSearchQuery(searchQuery);
        searchQueryEvent.setDate(new Date());
    }

    @Test
    public void save() throws Exception {
        defaultSearchQueryEventRepository.save(searchQueryEvent);
    }


    @Test
    public void getLastSearchQueryEvent() throws Exception {
        save();
        SearchQueryEvent searchQueryEvent = defaultSearchQueryEventRepository.getLast();
        Assert.assertEquals(this.searchQueryEvent.getDate(),searchQueryEvent.getDate());

    }


    @Test
    public void getAllSearchQueryEvents() throws Exception {
        List<SearchQueryEvent> events = defaultSearchQueryEventRepository.getAll();
        Assert.assertNotNull("SearchQueryEventList is not null", events);
    }

}