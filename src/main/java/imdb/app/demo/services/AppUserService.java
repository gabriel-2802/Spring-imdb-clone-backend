package imdb.app.demo.services;

import imdb.app.demo.entities.AppUser;
import imdb.app.demo.entities.LoginRequest;
import imdb.app.demo.entities.RegisterRequest;
import imdb.app.demo.entities.Role;
import imdb.app.demo.repos.AppUserRepository;
import imdb.app.demo.repos.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Security;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class AppUserService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    public ResponseEntity<String> register(RegisterRequest registerRequest) {
        if (appUserRepository.existsAppUserByUsername(registerRequest.getUsername())) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }

        AppUser newUser = new AppUser();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        Role roles = roleRepository.findByAuthority("USER").get();
        newUser.setRoles(new HashSet<Role>() {{
            add(roles);
        }});

        appUserRepository.save(newUser);
        return ResponseEntity.ok("User registered successfully!");
    }

    public ResponseEntity<String> login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok("User logged in successfully!");

    }
}
