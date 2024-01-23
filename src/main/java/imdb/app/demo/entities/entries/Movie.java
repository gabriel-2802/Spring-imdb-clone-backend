package imdb.app.demo.entities.entries;

import imdb.app.demo.entities.request_response.MovieRequest;
import jakarta.persistence.Entity;

import java.util.ArrayList;

@Entity
public class Movie extends Production {
    public Movie() {

    }
    public Movie(MovieRequest movieRequest) {
        this.title = movieRequest.getTitle();
        this.description = movieRequest.getDescription();
        this.actors = new ArrayList<>(movieRequest.getActors());
        this.genres = new ArrayList<>(movieRequest.getGenres());
        this.directors = new ArrayList<>(movieRequest.getDirectors());
        this.duration = movieRequest.getDuration();
        this.releaseYear = movieRequest.getReleaseYear();

        this.rating = 0;
        this.reviews = new ArrayList<>();
    }

    @Override
    public ProductionTypes getType() {
        return ProductionTypes.MOVIE;
    }
}
