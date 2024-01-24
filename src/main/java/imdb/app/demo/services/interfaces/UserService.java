package imdb.app.demo.services.interfaces;

import imdb.app.demo.entities.AppUser;
import imdb.app.demo.entities.ReviewRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<AppUser> loginInfo(String username);

    ResponseEntity<String> addMovieReview(Integer movieId, ReviewRequest review, String username);
}
