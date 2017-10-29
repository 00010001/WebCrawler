package service.crawlers;

import model.JobPosting;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PracaCrawlerTest {

    PracaCrawler pracaCrawler;

    @Before
    public void setup() {
        pracaCrawler = new PracaCrawler();
    }

    @Test
    public void shouldWorkForKeywordAndLocation() throws Exception {
        String keyword = "Programista";
        String location = "Katowice";
        List<JobPosting> jobPostingList = new ArrayList<>();

        jobPostingList = pracaCrawler.crawl(keyword,location, jobPostingList);
        Assert.assertTrue(jobPostingList.size()>0);
    }

    @Test
    public void shouldWorkForKeywordAndEmptyLocation() throws Exception {
        String keyword = "Software Developer";
        String location = "";
        List<JobPosting> jobPostingList = new ArrayList<>();

        jobPostingList = pracaCrawler.crawl(keyword,location, jobPostingList);
        Assert.assertTrue(jobPostingList.size()>0);
    }
}