package imdb.app.demo.services;

import imdb.app.demo.entities.AppUser;
import imdb.app.demo.entities.Review;
import imdb.app.demo.entities.ReviewRequest;
import imdb.app.demo.entities.entries.Movie;
import imdb.app.demo.repos.MovieRepository;
import imdb.app.demo.repos.ProductionRepository;
import imdb.app.demo.repos.ReviewRepository;
import imdb.app.demo.repos.UserRepository;
import imdb.app.demo.security.JwtGenerator;
import imdb.app.demo.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceV1 implements UserService {
    private final UserRepository userRepository;
    private final ProductionRepository productionRepository;
    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public ResponseEntity<AppUser> loginInfo(String username) {
        try {
            AppUser user = userRepository.findByUsername(username);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @Override
    public ResponseEntity<String> addMovieReview(Integer movieId, ReviewRequest review,
                                                 String username) {
        Optional<Movie> movieEntry = movieRepository.findById(movieId);
        if (movieEntry.isEmpty()) {
            return ResponseEntity.badRequest().body("Movie not found!");
        }

        if (reviewRepository.findByProductionIdAndUserUsername(movieId, username)) {
            return ResponseEntity.badRequest().body("Review already exists!");
        }

        Movie movie = movieEntry.get();
        Review newReview = new Review(review.getTitle(), review.getContent(),
                review.getRating(), movie, userRepository.findByUsername(username));

        try {
            reviewRepository.save(newReview);
            return ResponseEntity.ok("Review added successfully!");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Review not saved!");
        }
    }


}
