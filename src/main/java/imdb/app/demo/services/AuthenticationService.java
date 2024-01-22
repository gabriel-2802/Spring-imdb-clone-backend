package imdb.app.demo.services;

import imdb.app.demo.entities.*;
import imdb.app.demo.entities.request_response.AdminRequest;
import imdb.app.demo.entities.request_response.AuthResponse;
import imdb.app.demo.entities.request_response.LoginRequest;
import imdb.app.demo.entities.request_response.RegisterRequest;
import imdb.app.demo.repos.AppUserRepository;
import imdb.app.demo.repos.RoleRepository;
import imdb.app.demo.security.JwtGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import imdb.app.demo.security.Constants;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;

    public ResponseEntity<String> register(RegisterRequest registerRequest) {
        if (appUserRepository.existsAppUserByUsername(registerRequest.getUsername())) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }

        AppUser newUser = new AppUser();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        Role roles = roleRepository.findByAuthority(Authority.USER.toString()).get();
        newUser.setRoles(new HashSet<Role>() {{
            add(roles);
        }});

        appUserRepository.save(newUser);
        return ResponseEntity.ok("User registered successfully!");
    }

    public ResponseEntity<AuthResponse> login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                            loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtGenerator.generateToken(authentication);

            return ResponseEntity.ok(new AuthResponse(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body(new AuthResponse("Invalid username or password"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new AuthResponse("Error: " + e.getMessage()));
        }
    }

    public ResponseEntity<String> registerAdmin(AdminRequest adminRequest) {
        if (adminRequest.getAdminKey() != Constants.ADMIN_KEY) {
            return ResponseEntity.badRequest().body("Error: Invalid admin key!");
        }


        if (appUserRepository.existsAppUserByUsername(adminRequest.getUsername())) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }

        AppUser newUser = new AppUser();
        newUser.setUsername(adminRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(adminRequest.getPassword()));
        Role roles = roleRepository.findByAuthority(Authority.ADMIN.toString()).get();
        newUser.setRoles(new HashSet<Role>() {{
            add(roles);
        }});

        appUserRepository.save(newUser);
        return ResponseEntity.ok("Admin registered successfully!");
    }
}
