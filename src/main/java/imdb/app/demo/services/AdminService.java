package imdb.app.demo.services;

import imdb.app.demo.entities.request_response.AdminRequest;
import org.springframework.http.ResponseEntity;

public interface AdminService {
    ResponseEntity<?> showAllUsers();
    ResponseEntity<?> showAllMovies();
    ResponseEntity<?> showAllShows();
    ResponseEntity<?> showAllReviews();
}
