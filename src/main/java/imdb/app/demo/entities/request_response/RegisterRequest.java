package imdb.app.demo.entities.request_response;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
}
