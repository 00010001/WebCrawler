package service.crawlers;

import model.JobPosting;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PracujCrawlerTest {
    PracujCrawler pracujCrawler;

    @Before
    public void setup() {
        pracujCrawler = new PracujCrawler();
    }

    @Test
    public void shouldWorkForKeywordAndLocation() throws Exception {
        String keyword = "Java";
        String location = "Katowice";
        List<JobPosting> jobPostingList = new ArrayList<>();

        jobPostingList = pracujCrawler.crawl(keyword,location, jobPostingList);
        Assert.assertTrue(jobPostingList.size()>0);
    }

    @Test
    public void shouldWorkForKeywordAndEmptyLocation() throws Exception {
        String keyword = "Senior Java";
        String location = "";
        List<JobPosting> jobPostingList = new ArrayList<>();

        jobPostingList = pracujCrawler.crawl(keyword,location, jobPostingList);
        Assert.assertTrue(jobPostingList.size()>0);
    }
}