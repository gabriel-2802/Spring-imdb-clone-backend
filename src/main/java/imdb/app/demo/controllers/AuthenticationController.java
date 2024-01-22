package imdb.app.demo.controllers;

import imdb.app.demo.entities.AuthResponse;
import imdb.app.demo.entities.LoginRequest;
import imdb.app.demo.entities.RegisterRequest;
import imdb.app.demo.repos.AppUserRepository;
import imdb.app.demo.repos.RoleRepository;
import imdb.app.demo.security.JwtGenerator;
import imdb.app.demo.services.AppUserService;
import imdb.app.demo.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("imdb/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final AppUserService appUserService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final JwtGenerator jwtGenerator;

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
}
