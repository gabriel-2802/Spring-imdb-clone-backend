package imdb.app.demo.services;

import imdb.app.demo.entities.WatchListItem;
import imdb.app.demo.entities.entries.Production;
import imdb.app.demo.entities.users.AppUser;
import imdb.app.demo.entities.Review;
import imdb.app.demo.entities.users.ReviewRequest;
import imdb.app.demo.entities.entries.Movie;
import imdb.app.demo.repos.MovieRepository;
import imdb.app.demo.repos.ReviewRepository;
import imdb.app.demo.repos.UserRepository;
import imdb.app.demo.services.interfaces.ProductionService;
import imdb.app.demo.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceV1 implements UserService {
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;
    private final ProductionService productionService;

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

        if (reviewRepository.existsByProductionIdAndUserUsername(movieId, username)) {
            return ResponseEntity.badRequest().body("Review already exists!");
        }

        Movie movie = movieEntry.get();
        Review newReview = new Review(review.getTitle(), review.getContent(),
                review.getRating(), movie, userRepository.findByUsername(username));

        try {
            reviewRepository.save(newReview);
            productionService.updateProductionRating(movieId);
            return ResponseEntity.ok("Review added successfully!");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Review not saved! " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<String> deleteReview(Integer id) {
        Optional<Review> review = reviewRepository.findById(id);
        if (review.isEmpty()) {
            return ResponseEntity.badRequest().body("Review not found!");
        }

        try {
            int prodId = review.get().getProdId();
            reviewRepository.deleteById(id);
            productionService.updateProductionRating(prodId);

            return ResponseEntity.ok("Review deleted successfully!");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Review not deleted!");
        }
    }

    @Override
    public ResponseEntity<List<Review>> getReviews(String username) {
       try {
              List<Review> reviews = reviewRepository.findAllByUserUsername(username);
              return ResponseEntity.ok(reviews);
         }
         catch (Exception e) {
              return ResponseEntity.badRequest().build();
       }
    }

    @Override
    public ResponseEntity<String> updateReview(Integer id, ReviewRequest review) {
        Optional<Review> reviewEntry = reviewRepository.findById(id);
        if (reviewEntry.isEmpty()) {
            return ResponseEntity.badRequest().body("Review not found!");
        }

        Review existingReview = reviewEntry.get();
        existingReview.setTitle(review.getTitle());
        existingReview.setContent(review.getContent());
        existingReview.setRating(review.getRating());

        try {
            reviewRepository.save(existingReview);
            productionService.updateProductionRating(existingReview.getProdId());
            return ResponseEntity.ok("Review updated successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Review not updated!");
        }
    }

    @Override
    public ResponseEntity<List<Production>> getWatchList(String username) {
        try {
            AppUser user = userRepository.findByUsername(username);
            return ResponseEntity.ok(user.getWatchListItems().stream().map(WatchListItem::getProduction).toList());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<String> addToWatchList(Integer id, String username) {
        AppUser user;
        try {
            user = userRepository.findByUsername(username);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("User not found!");
        }
        Movie movie;
        try {
            movie = movieRepository.findById(id).get();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Movie not found!");
        }

        if (user.getWatchListItems().stream().anyMatch(watchListItem -> watchListItem.getProduction().getId().equals(id))) {
            return ResponseEntity.badRequest().body("Movie already in watchlist!");
        }

        WatchListItem watchListItem = new WatchListItem(user, movie);
        try {
            user.getWatchListItems().add(watchListItem);
            userRepository.save(user);
            return ResponseEntity.ok("Movie added to watchlist!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Movie not added to watchlist! " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<String> removeFromWatchList(Integer id, String username) {
        AppUser user;
        try {
            user = userRepository.findByUsername(username);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("User not found!");
        }
        Movie movie;
        try {
            movie = movieRepository.findById(id).get();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Movie not found!");
        }

        if (user.getWatchListItems().stream().noneMatch(watchListItem -> watchListItem.getProduction().getId().equals(id))) {
            return ResponseEntity.badRequest().body("Movie not in watchlist!");
        }

        try {
            user.getWatchListItems().removeIf(watchListItem -> watchListItem.getProduction().getId().equals(id));
            userRepository.save(user);
            return ResponseEntity.ok("Movie removed from watchlist!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Movie not removed from watchlist! " + e.getMessage());
        }
    }
}
