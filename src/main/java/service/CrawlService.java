package service;

import com.google.common.collect.Lists;
import model.JobPosting;
import model.SearchQuery;
import model.SearchResult;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import service.crawlers.PracaCrawler;
import service.crawlers.PracujCrawler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CrawlService {

    private static final Logger LOGGER = LogManager.getLogger(CrawlService.class);

    private List<JobPosting> jobPostingList;
    private SearchQuery searchQuery;



    public CrawlService(SearchQuery searchQuery) {
        jobPostingList = new ArrayList<>();
        this.searchQuery = searchQuery;
        LOGGER.info("[keywords = " + searchQuery.getKeywords() + "],[location = " + searchQuery.getLocation() + "]");
    }

    private void crawlPraca(String keyword, String location) {
        PracaCrawler pracaCrawler = new PracaCrawler();
        pracaCrawler.crawl(keyword, location, this.jobPostingList);
    }

    private void crawlPracuj(String keyword, String location) {
        PracujCrawler pracujCrawler = new PracujCrawler();
        pracujCrawler.crawl(keyword, location, this.jobPostingList);
    }

    public SearchResult getSearchResults() {
        for (String keyword : searchQuery.getKeywords()) {
            LOGGER.info("Getting results for [keyword = " + keyword + "],[location = " + searchQuery.getLocation() + "]");

            crawlPraca(keyword,searchQuery.getLocation());
            crawlPracuj(keyword,searchQuery.getLocation());
        }

        Collections.sort(jobPostingList);
        jobPostingList = Lists.reverse(jobPostingList);

        return new SearchResult(jobPostingList);
    }
}
