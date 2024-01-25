package imdb.app.demo.controllers;

import imdb.app.demo.entities.AppUser;
import imdb.app.demo.entities.Review;
import imdb.app.demo.entities.ReviewRequest;
import imdb.app.demo.security.JwtGenerator;
import imdb.app.demo.services.interfaces.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("imdb/users")
public class UserController {
    private final UserService userService;
    private final JwtGenerator jwtGenerator;

    @GetMapping("/login-info")
    public ResponseEntity<AppUser> loginInfo(HttpServletRequest request) {
        System.out.println("login-info");
        return userService.loginInfo(jwtGenerator.getUsernameFromToken(getToken(request)));
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }

        return null;

    }

    @GetMapping("/home")
    public String home() {
        return "Welcome to your HomePage!";
    }

    @PostMapping("add-movie-review/{id}")
    public ResponseEntity<String> addMovieReview(@PathVariable Integer id, @RequestBody ReviewRequest review, HttpServletRequest request) {
        String token = getToken(request);
        String username = jwtGenerator.getUsernameFromToken(token);
        return userService.addMovieReview(id, review, username);
    }

    @DeleteMapping("/delete-review/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Integer id) {
        return userService.deleteReview(id);
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getReviews(HttpServletRequest request) {
        String token = getToken(request);
        String username = jwtGenerator.getUsernameFromToken(token);
        return userService.getReviews(username);
    }

    @PutMapping("/update-review/{id}")
    public ResponseEntity<String> updateReview(@PathVariable Integer id, @RequestBody ReviewRequest review) {
        return userService.updateReview(id, review);
    }


}
