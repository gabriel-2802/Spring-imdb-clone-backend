package imdb.app.demo.services.interfaces;

import imdb.app.demo.entities.AppUser;
import imdb.app.demo.entities.entries.Production;
import imdb.app.demo.entities.request_response.MovieRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminService {
    ResponseEntity<List<AppUser>> showAllUsers();

    ResponseEntity<List<Production>> showAllProduction();

    ResponseEntity<String> addMovie(MovieRequest production);

    ResponseEntity<String> deleteMovie(Integer id);

    ResponseEntity<String> updateMovie(Integer id, MovieRequest production);
}
