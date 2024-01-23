package imdb.app.demo.services;

import imdb.app.demo.entities.AppUser;
import imdb.app.demo.entities.entries.Movie;
import imdb.app.demo.entities.entries.Production;
import imdb.app.demo.entities.request_response.MovieRequest;
import imdb.app.demo.repos.AppUserRepository;
import imdb.app.demo.repos.MovieRepository;
import imdb.app.demo.repos.ProductionRepository;
import imdb.app.demo.services.interfaces.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceV1 implements AdminService {
    private final AppUserRepository userRepository;
    private final ProductionRepository productionRepository;
    private final MovieRepository movieRepository;


    @Override
    public ResponseEntity<List<AppUser>> showAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @Override
    public ResponseEntity<List<Production>> showAllProduction() {
        return ResponseEntity.ok((List<Production>) (productionRepository.findAll()));
    }

    @Override
    public ResponseEntity<String> addMovie(MovieRequest production) {
        if (movieRepository.existsByTitleAndReleaseYear(production.getTitle(), production.getReleaseYear())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Movie already exists");
        }

        Movie movie = new Movie(production);
        movieRepository.save(movie);
        return ResponseEntity.ok("Movie added successfully");
    }

    @Override
    public ResponseEntity<String> deleteMovie(Integer id) {
        if (!movieRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found");
        }

        movieRepository.deleteById(id);
        return ResponseEntity.ok("Movie deleted successfully");
    }

    @Override
    public ResponseEntity<String> updateMovie(Integer id, MovieRequest production) {
        if (!movieRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found");
        }

        Movie existingMovie = movieRepository.findById(id).get();

        // update
        existingMovie.setActors(production.getActors());
        existingMovie.setDescription(production.getDescription());
        existingMovie.setDirectors(production.getDirectors());
        existingMovie.setDuration(production.getDuration());
        existingMovie.setGenres(production.getGenres());
        existingMovie.setReleaseYear(production.getReleaseYear());
        existingMovie.setTitle(production.getTitle());

        movieRepository.save(existingMovie);
        return ResponseEntity.ok("Movie updated successfully");

    }
}
