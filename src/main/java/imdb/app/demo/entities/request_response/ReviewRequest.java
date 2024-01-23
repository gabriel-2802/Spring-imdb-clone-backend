package imdb.app.demo.entities.request_response;

import lombok.Data;

@Data
public class ReviewRequest {
    private String title;
    private String content;
    private Float rating;
}
