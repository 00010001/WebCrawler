package repository;

import model.SearchQueryEvent;

import java.util.List;

public interface SearchQueryEventRepository {
    void save(SearchQueryEvent searchQueryEvent);
    List<SearchQueryEvent> getAll();
    SearchQueryEvent getLast();
}
