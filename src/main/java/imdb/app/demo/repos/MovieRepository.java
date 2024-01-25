package imdb.app.demo.repos;

import imdb.app.demo.entities.entries.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    public boolean existsByTitleAndReleaseYear(String title, int releaseYear);
}
