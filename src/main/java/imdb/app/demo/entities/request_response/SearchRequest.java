package imdb.app.demo.entities.request_response;

import imdb.app.demo.entities.entries.ProductionTypes;
import lombok.Data;

import java.lang.reflect.Type;
import java.util.List;

@Data
public class SearchRequest {
    private String title;
    private int year;
    private List<String> genres;
    private String type;
    private float rating;

    public ProductionTypes getType() {
        return ProductionTypes.valueOf(type);
    }
}
