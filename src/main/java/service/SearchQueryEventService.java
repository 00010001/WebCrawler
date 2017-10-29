package service;

import model.SearchQuery;
import model.SearchQueryEvent;
import repository.SearchQueryEventRepository;
import repository.impl.DefaultSearchQueryEventRepositoryImpl;

import java.util.Date;
import java.util.List;

public class SearchQueryEventService {

    private SearchQueryEventRepository searchQueryEventRepository;

    public SearchQueryEventService() {
        searchQueryEventRepository = new DefaultSearchQueryEventRepositoryImpl();
    }

    public void save(SearchQuery searchQuery) {
        SearchQueryEvent searchQueryEvent = new SearchQueryEvent();
        searchQueryEvent.setDate(new Date());
        searchQueryEvent.setSearchQuery(searchQuery);

        searchQueryEventRepository.save(searchQueryEvent);
    }

    public List<SearchQueryEvent> getEventsFromDB(){
        return searchQueryEventRepository.getAll();
    }

    public SearchQueryEvent getLastEventFromDB(){
        return searchQueryEventRepository.getLast();
    }
}
