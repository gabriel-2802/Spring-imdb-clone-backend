package imdb.app.demo.entities;

import lombok.Data;

@Data
public class ReviewRequest {
    private String title;
    private String content;
    private float rating;
}
