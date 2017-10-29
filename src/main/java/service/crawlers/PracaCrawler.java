package service.crawlers;

import model.JobPosting;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PracaCrawler {

    private List<JobPosting> jobPostingList;
    private String firstSearchResultUrl;

    //TODO fix node.js

    public List<JobPosting> crawl(String keyword, String location, List<JobPosting> jobPostingList) {

        buildSearchURL(keyword, location);
        this.jobPostingList = jobPostingList;

        List<String> searchResultUrls = new ArrayList<>();
        searchResultUrls.add(firstSearchResultUrl);

        if (searchResultUrls.size() > 0) {
            for (String searchResultUrl : searchResultUrls) {
                Document doc = CrawlerUtils.parseDocByJsoup(searchResultUrl);
                Elements jobPostings = CrawlerUtils.parseJobPostings(doc,".announcement-box");
                validateJobPostingsFromPraca(keyword, jobPostings);
            }
        }

        return this.jobPostingList;
    }

    private void buildSearchURL(String keyword, String location) {

        String keywordWithPolishChars = "";
        try {
            keywordWithPolishChars = URLEncoder.encode(keyword, "UTF-8");
            keywordWithPolishChars = keywordWithPolishChars.replaceAll("\\+", " ");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String keywordWithoutPolishChars = keywordWithPolishChars.replaceAll("%C5%82", "l");


        String firstParameterInURL = keywordWithoutPolishChars.replaceAll(" ", ",");
        String secondParameterInURL = keywordWithPolishChars.replaceAll(" ", "+");

        if (!location.equals("")) {
            firstSearchResultUrl = "https://www.praca.pl/s-" + firstParameterInURL + "," + location + ".html?p=" + secondParameterInURL + "+" + location;
        } else {
            firstSearchResultUrl = "https://www.praca.pl/s-" + firstParameterInURL + ".html?p=" + secondParameterInURL;
        }

    }



    private void validateJobPostingsFromPraca(String keyword, Elements jobPostings) {
        for (Element jobPosting : jobPostings) {

            String jobTitle = jobPosting.select(".title").html();
            String jobURL = jobPosting.select("a.title").attr("abs:href");
            String jobDescription = jobPosting.select(".company").text() + " - " + jobPosting.select(".announcement-area").text();
            String dateString = jobPosting.select(".data-block").text();
            try {
                dateString = URLEncoder.encode(dateString, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            Date date = null;
            if (dateString.matches("\\d{1,2}\\+godz.")) {
                date = new Date();
            }
            if (dateString.matches("\\d\\+dzie%C5%84")) {
                date = new Date(System.currentTimeMillis()-24*60*60*1000);
            }
            if (dateString.matches("\\d{1,2}\\+dni")) {
                int daysPast = Integer.parseInt(dateString.substring(0,1));
                date = new Date(System.currentTimeMillis()-daysPast*24*60*60*1000);
            }

            boolean jobTitleContainsSearchKeyword = jobTitle.toLowerCase().contains(keyword.toLowerCase());

            if (jobTitleContainsSearchKeyword) {
                if (date != null) {
                    JobPosting job = new JobPosting(jobTitle, jobURL, jobDescription, "praca.pl",date);
                    jobPostingList.add(job);
                } else {
                    JobPosting job = new JobPosting(jobTitle, jobURL, jobDescription, "praca.pl");
                    jobPostingList.add(job);

                }
            }
        }
    }
}
