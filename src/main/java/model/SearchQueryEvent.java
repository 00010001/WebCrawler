package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class SearchQueryEvent implements Comparable<SearchQueryEvent> {

    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Id private String id;

    private Date date;

    private SearchQuery searchQuery;

    @Override
    public int compareTo(SearchQueryEvent event) {
        return getDate().compareTo(event.getDate());
    }
}
