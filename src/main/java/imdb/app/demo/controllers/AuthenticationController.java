package imdb.app.demo.controllers;

import imdb.app.demo.entities.request_response.AdminRequest;
import imdb.app.demo.entities.request_response.AuthResponse;
import imdb.app.demo.entities.request_response.LoginRequest;
import imdb.app.demo.entities.request_response.RegisterRequest;
import imdb.app.demo.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("imdb/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService appUserService;

    @GetMapping()
    public String hello() {
        return "Hello from AuthenticationController";
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        return appUserService.register(registerRequest);
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return appUserService.login(loginRequest);
    }

    @PostMapping("register/admin")
    public ResponseEntity<String> registerAdmin(@RequestBody AdminRequest adminRequest) {
        return appUserService.registerAdmin(adminRequest);
    }
}
