package service;

import model.PredefinedSelect;
import model.SearchQuery;
import model.SearchQueryEvent;
import repository.SearchQueryEventRepository;
import repository.impl.DefaultSearchQueryEventRepositoryImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class SearchQueryService {

    public SearchQuery buildFromUserInput(HttpServletRequest req) {

        String predefinedSelect = req.getParameter("predefinedSelect");
        String keyword = req.getParameter("keyword");
        String keyword2 = req.getParameter("keyword2");
        String keyword3 = req.getParameter("keyword3");
        String location = req.getParameter("location");
        //TODO providers
        //String providers = req.getParameter("providers");
        List<String> keywords;
        if (!predefinedSelect.equals("")) {
            keywords = processPredefinedSelect(predefinedSelect);
        } else {
            keywords = processKeywords(keyword, keyword2, keyword3);
        }

        //processProviders(providers);

        return new SearchQuery(keywords, location);
    }

    private List<String> processPredefinedSelect(String predefinedSelect) {
        List<String> keywords = new ArrayList<>();
        switch (predefinedSelect) {
            case "1":
                keywords = PredefinedSelect.MLODSZY_PROGRAMISTA_JAVA_JUNIOR_JAVA.getKeywords();
                break;
        }

        return keywords;

    }

    private List<String> processKeywords(String keyword, String keyword2, String keyword3) {
        List<String> keywords = new ArrayList<>();
        keywords.add(keyword);
        if (!keyword2.equals("")) {
            keywords.add(keyword2);
        }
        if (!keyword3.equals("")) {
            keywords.add(keyword3);
        }
        return keywords;
    }


}
