package controller.api;


import com.google.gson.Gson;
import model.SearchQueryEvent;
import service.SearchQueryEventService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/events/")
public class SearchQueryEventController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SearchQueryEventService searchQueryEventService = new SearchQueryEventService();
        List<SearchQueryEvent> events =
                searchQueryEventService.getEventsFromDB();

        resp.setContentType("application/json");
// Get the printwriter object from response to write the required json object to the output stream
        PrintWriter out = resp.getWriter();
// Assuming your json object is **jsonObject**, perform the following, it will return your json object

        String json = new Gson().toJson(events);
        out.print(json);
        out.flush();
    }


}
