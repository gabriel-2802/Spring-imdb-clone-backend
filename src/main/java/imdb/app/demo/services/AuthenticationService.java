package imdb.app.demo.services;

import imdb.app.demo.entities.request_response.AdminRequest;
import imdb.app.demo.entities.request_response.AuthResponse;
import imdb.app.demo.entities.request_response.LoginRequest;
import imdb.app.demo.entities.request_response.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<String> register(RegisterRequest registerRequest);
    ResponseEntity<AuthResponse> login(LoginRequest loginRequest);
    ResponseEntity<String> registerAdmin(AdminRequest adminRequest);

}
