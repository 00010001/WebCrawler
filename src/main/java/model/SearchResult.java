package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SearchResult {

    private String _id;
    List<JobPosting> jobPostingList;

    public SearchResult(List<JobPosting> jobPostingList) {
        this.jobPostingList = jobPostingList;
    }
}
