package service.crawlers;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

class CrawlerUtils {

    private static final Logger LOGGER = LogManager.getLogger(CrawlerUtils.class);

    static Document parseDocByJsoup(String url) {
        LOGGER.info("Parsing document from " + url);

        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    static Elements parseJobPostings(Document doc, String cssQuery) {
        Elements jobPostings = null;
        if (doc != null) {
            jobPostings = doc.select(cssQuery);
        }
        return jobPostings;
    }

//    private Elements parseJobPostingsFromPracuj(Document doc, String cssQuery) {
//        Elements jobPostings = null;
//        if (doc != null) {
//            jobPostings = doc.select("[itemtype=http://schema.org/JobPosting]");
//        }
//".announcement-box"
//        return jobPostings;
//    }
}
