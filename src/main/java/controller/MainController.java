package controller;

import model.SearchQuery;
import model.SearchResult;
import service.ControllerUtils;
import service.CrawlService;
import service.SearchQueryEventService;
import service.SearchQueryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/search")
public class MainController extends HttpServlet {

    private SearchQuery searchQuery;
    private SearchResult searchResult;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAttributesAndRequestDispatcher(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ControllerUtils.setEncoding(req,resp);
        SearchQueryService searchQueryService = new SearchQueryService();
        searchQuery = searchQueryService.buildFromUserInput(req);
        SearchQueryEventService searchQueryEventService = new SearchQueryEventService();
        searchQueryEventService.save(searchQuery);
        initializeCrawlService();
        setAttributesAndRequestDispatcher(req, resp);
    }


    private void initializeCrawlService(){
        CrawlService crawlService = new CrawlService(searchQuery);
        searchResult = crawlService.getSearchResults();
    }

    private void setAttributesAndRequestDispatcher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("searchQuery",searchQuery);
        req.setAttribute("searchResult",searchResult);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("search.jsp");
        requestDispatcher.forward(req, resp);
    }




}
