package repository.impl;

import model.SearchQueryEvent;
import repository.SearchQueryEventRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Collections;
import java.util.List;

public class DefaultSearchQueryEventRepositoryImpl implements SearchQueryEventRepository {

    //TODO how to catch exception when MongoDB server is offline?


    private EntityManager em;

    @Override
    public void save(SearchQueryEvent searchQueryEvent) {

        createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(searchQueryEvent);
        em.flush();
        tx.commit();
    }

    @Override
    public List<SearchQueryEvent> getAll() {

        createEntityManager();

        String nativeQuery = "db.SearchQueryEvent.find({ '$query': {}})";

        List<SearchQueryEvent> events = em.createNativeQuery(nativeQuery, SearchQueryEvent.class)
                .getResultList();

        return events;
    }

    @Override
    public SearchQueryEvent getLast() {
        List<SearchQueryEvent> events = getAll();
        Collections.sort(events);
        Collections.reverse(events);
        return events.get(0);
    }

    private void createEntityManager(){
        if(!(em == null)){
            return;
        }
        em = Persistence.createEntityManagerFactory("primary").createEntityManager();
    }
}
