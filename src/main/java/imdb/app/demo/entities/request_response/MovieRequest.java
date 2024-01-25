package imdb.app.demo.entities.request_response;

import lombok.Data;

import java.util.List;

@Data
public class MovieRequest {
    private String title;
    private String description;
    private List<String> actors;
    private List<String> genres;
    private List<String> directors;
    private int duration;
    private int releaseYear;
}
