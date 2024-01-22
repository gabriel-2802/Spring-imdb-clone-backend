package imdb.app.demo.entities.request_response;

import lombok.Data;

@Data
public class AuthResponse {
    private String accessToken;
    private String tokenType;

    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
        tokenType = "Bearer ";
    }
}
