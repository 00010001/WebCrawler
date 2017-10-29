package service.crawlers;

import model.JobPosting;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PracujCrawler {

    private static final Logger LOGGER = LogManager.getLogger(PracujCrawler.class);

    private List<JobPosting> jobPostingList;
    private String firstSearchResultUrl;

    public List<JobPosting> crawl(String keyword, String location, List<JobPosting> jobPostingList) {

        keyword = removeWhitespaces(keyword);

        //TODO fix searchresultURLs

        this.jobPostingList = jobPostingList;

        List<String> searchResultUrls = new ArrayList<>();
        firstSearchResultUrl = "https://www.pracuj.pl/praca/" + keyword + ";kw/";
        checkIfLocationIsNotEmpty(location);
        searchResultUrls.add(firstSearchResultUrl);

        determineIfThereAreMoreSearchResultPages(searchResultUrls);

        if(searchResultUrls.size()>0){
            for (String searchResultUrl : searchResultUrls) {
                Document doc = CrawlerUtils.parseDocByJsoup(searchResultUrl);
                Elements jobPostings = CrawlerUtils.parseJobPostings(doc,"[itemtype=http://schema.org/JobPosting]");
                validateJobPostingsFromPracuj(keyword, jobPostings);
            }
        }

        return this.jobPostingList;
    }

    private void determineIfThereAreMoreSearchResultPages(List<String> searchResultUrls) {


        Document doc = CrawlerUtils.parseDocByJsoup(firstSearchResultUrl);
        LOGGER.info("Determining if there are more than one page of results");

        List<Integer> searchResultPagesNumberList = new ArrayList<>();
        for (Element element : doc.select(".desktopPagin_item_link")) {
            if (element.text().matches("\\d+")) {
                searchResultPagesNumberList.add(Integer.parseInt(element.text()));
            }
        }

        Optional<Integer> searchResultPagesNumber = searchResultPagesNumberList.stream().max(Comparator.naturalOrder());
        if (searchResultPagesNumber.isPresent()) {
            for (int i = 1; i < searchResultPagesNumber.get(); i++) {
                String url = firstSearchResultUrl + "?pn=" + (i + 1);
                searchResultUrls.add(url);
                if(i == 5){
                    break;
                }
            }
        }

    }

    private void checkIfLocationIsNotEmpty(String location) {
        if (!location.equals("")) {
            firstSearchResultUrl = firstSearchResultUrl + location + ";wp";
        }
    }

    private void validateJobPostingsFromPracuj(String keyword, Elements jobPostings) {
        for (Element jobPosting : jobPostings) {

            String jobTitle = jobPosting.select(".o-list_item_link_name").text();
            String jobURL = jobPosting.select(".o-list_item_link_name").attr("abs:href");
            String jobDescription = jobPosting.text();
            String dateString = jobPosting.select(".o-list_item_desc_date").text();
            Date date = parseDateFromJobPosting(dateString);

            keyword = bringBackWhitespaces(keyword);
            boolean jobTitleContainsSearchKeyword = jobTitle.toLowerCase().contains(keyword.toLowerCase());


            if (jobTitleContainsSearchKeyword) {
                JobPosting job = new JobPosting(jobTitle, jobURL, jobDescription, "pracuj.pl", date);
                jobPostingList.add(job);
            }
        }
    }

    private String removeWhitespaces(String string){
        if(string.contains(" ")){
            return string.replaceAll("\\s+","%20");
        }
        return string;
    }

    private String bringBackWhitespaces(String string){
        if(string.contains("%20")){
            return string.replaceAll("%20"," ");
        }
        return string;
    }

    private Date parseDateFromJobPosting(String dateString) {
        SimpleDateFormat dateFormat = determineDateFormatFromJobPostingField(dateString);
        Date date = null;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private SimpleDateFormat determineDateFormatFromJobPostingField(String dateString) {
        SimpleDateFormat dateFormat;
        if (dateString.matches("\\d{4}[-]\\d{2}[-]\\d{2}")) {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        } else {
            dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        }
        return dateFormat;
    }




}
