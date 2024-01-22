package imdb.app.demo.entities;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
