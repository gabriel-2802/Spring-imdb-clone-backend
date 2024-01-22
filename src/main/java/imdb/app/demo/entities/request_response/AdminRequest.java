package imdb.app.demo.entities.request_response;

import lombok.Data;

@Data
public class AdminRequest {
    private String username;
    private String password;
    private int adminKey;
}
