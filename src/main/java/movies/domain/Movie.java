package movies.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "movies")
public class Movie {
    @Id
    private String id;  // Maps to _id field in MongoDB

    @Field("show_id")
    private String showId;

    private String type;

    private String title;

    private String director;

    private String cast;

    private String country;

    @Field("date_added")
    private String dateAdded;

    @Field("release_year")
    private Integer releaseYear;  // Use Integer as it is a number in DB

    private String duration;

    @Field("listed_in")
    private String listedIn;

    private String description;
}
