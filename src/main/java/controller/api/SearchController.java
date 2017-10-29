package controller.api;

import com.google.gson.Gson;
import model.SearchQuery;
import model.SearchResult;
import service.ControllerUtils;
import service.CrawlService;
import service.SearchQueryEventService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/searchapi")
public class SearchController extends HttpServlet {

    private SearchQuery searchQuery;
    private SearchResult searchResult;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        ControllerUtils.setEncoding(req, resp);
        searchQuery = new SearchQuery();
        List<String> keywords = new ArrayList<>();

        String keyword1 = req.getParameter("keyword1");
        String keyword2 = req.getParameter("keyword2");
        String keyword3 = req.getParameter("keyword3");



        if (!(keyword1 == null))
            keywords.add(keyword1);
        if (!(keyword2 == null))
            keywords.add(keyword2);
        if (!(keyword3 == null))
            keywords.add(keyword3);

        searchQuery.setKeywords(keywords);

        String location = req.getParameter("location");
        if (location == null)
            location = "";

        searchQuery.setLocation(location);


        SearchQueryEventService searchQueryEventService = new SearchQueryEventService();
        searchQueryEventService.save(searchQuery);
        initializeCrawlService();

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        String json = new Gson().toJson(searchResult.getJobPostingList());
        out.print(json);
        out.flush();
    }

    private void initializeCrawlService() {
        CrawlService crawlService = new CrawlService(searchQuery);
        searchResult = crawlService.getSearchResults();
    }


}
