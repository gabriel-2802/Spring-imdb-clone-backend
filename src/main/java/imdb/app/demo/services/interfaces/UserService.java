package imdb.app.demo.services.interfaces;

import imdb.app.demo.entities.entries.Production;
import imdb.app.demo.entities.users.AppUser;
import imdb.app.demo.entities.users.ReviewRequest;
import org.springframework.http.ResponseEntity;
import imdb.app.demo.entities.Review;

import java.util.List;

public interface UserService {
    ResponseEntity<AppUser> loginInfo(String username);

    ResponseEntity<String> addMovieReview(Integer movieId, ReviewRequest review, String username);

    ResponseEntity<String> deleteReview(Integer id);

    ResponseEntity<List<Review>> getReviews(String username);

    ResponseEntity<String> updateReview(Integer id, ReviewRequest review);

    ResponseEntity<List<Production>> getWatchList(String username);

    ResponseEntity<String> addToWatchList(Integer id, String username);

    ResponseEntity<String> removeFromWatchList(Integer id, String username);

    ResponseEntity<List<List<Production>>> viewHome(String username);
}
