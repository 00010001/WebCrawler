package sample;

import model.SearchQueryEvent;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.NDC;
import repository.impl.DefaultSearchQueryEventRepositoryImpl;

import java.util.Date;
import java.util.List;

public class Main {


    static Logger logger = Logger.getLogger(Main.class);


    public static void main(String[] args) {

        Date date = new Date();
        System.out.println(date);
        String query = "db.SearchQueryEvent.find({ date : ISODate(\"2017-10-24T21:21:23.275+02:00\")})";
//
//        DefaultSearchQueryEventRepositoryImpl defaultSearchQueryEventRepository = new DefaultSearchQueryEventRepositoryImpl();
//
//        List<SearchQueryEvent> events = defaultSearchQueryEventRepository.getAll();
//
//        for (SearchQueryEvent event : events) {
//            System.out.println(event);
//        }

//        logger.info("Awake awake. Put on thy strength.");
//
//        logger.info("Exiting Trivial.");


//       DefaultSearchQueryEventRepositoryImpl defaultSearchQueryEventRepository = new DefaultSearchQueryEventRepositoryImpl();
//       List<SearchQueryEvent> searchQueryEventList = defaultSearchQueryEventRepository.getAll();
//
//        for (SearchQueryEvent searchQueryEvent : searchQueryEventList) {
//            System.out.println(searchQueryEvent);
//        }


//        List<String> keywords = new ArrayList<>();
//        keywords.add("keyword1");
//        keywords.add("keyword2");
//        keywords.add("keyword3");
//
//        String location = "location";
//
//
//        SearchQuery searchQuery = new SearchQuery(keywords, location);
//
//        SearchQueryEvent searchQueryEvent = new SearchQueryEvent(searchQuery, new Date());



//        String id = "ObjectId(\"59ef3153ab8a3314384e4109\")";
//
//
//        session.beginTransaction();
//
//        SearchQueryEvent searchQueryEvent1 = (SearchQueryEvent) session.get(SearchQueryEvent.class, id);
//        System.out.println(searchQueryEvent1.getDate() + " - " + searchQueryEvent1.getSearchQuery());
//        session.getTransaction().commit();
//
//                session.getTransaction().rollback();
//                System.out.println("Persist failed...");


//       Person person = new Person();
//       person.setFirstName("dsa");
//       person.setLastName("dassad");
//
//
//
//        Logger log = Logger.getLogger(Main.class.getName());
//        log.info("This is info : ");
//
//
//        SessionManager sessionManager = new SessionManager();
//        OgmSession ogmSession = sessionManager.getCurrentSession();

//        List<String> keywords = new ArrayList<>();
//        keywords.add("Java");
//        String location = "katowice";
//        SearchQuery searchQuery = new SearchQuery(keywords,location);
//
//        SearchResult searchResult;
//
//        CrawlService crawlService = new CrawlService(searchQuery);
//        searchResult = crawlService.getSearchResults();


//

    }
}
