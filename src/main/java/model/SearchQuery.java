package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Embeddable
public class SearchQuery {

    @ElementCollection
    private List<String> keywords;
    //List<String> providers;
    private String location;

}
