package model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
public class JobPosting implements Comparable<JobPosting> {
    private String title;
    private String URL;
    private String description;
    private String provider;
    private Date date;
    private String dateString;


    public JobPosting(String title, String URL, String description, String provider, Date date) {
        this.title = title;
        this.URL = URL;
        this.description = description;
        this.provider = provider;
        this.date = date;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        this.dateString = dateFormat.format(date);
    }

    public JobPosting(String title, String URL, String description, String provider) {
        this.title = title;
        this.URL = URL;
        this.description = description;
        this.provider = provider;
        this.date = null;
        this.dateString = "";
    }

    @Override
    public int compareTo(JobPosting jobPosting) {
        return getDate().compareTo(jobPosting.getDate());
    }
}
