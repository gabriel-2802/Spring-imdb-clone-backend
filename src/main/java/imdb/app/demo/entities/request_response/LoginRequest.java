package imdb.app.demo.entities.request_response;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
