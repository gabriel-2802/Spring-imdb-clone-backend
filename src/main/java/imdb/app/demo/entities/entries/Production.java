package imdb.app.demo.entities.entries;

import imdb.app.demo.entities.Review;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Production {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    protected String title;
    protected String description;
    @ElementCollection
    protected List<String> actors;
    @ElementCollection
    protected List<String> genres;
    @ElementCollection
    protected List<String> directors;
    protected int duration;
    protected int releaseYear;

    protected float rating;
    @OneToMany(mappedBy = "production", cascade = CascadeType.ALL, orphanRemoval = true)
    protected List<Review> reviews;
    public abstract ProductionTypes getType();
    public void updateRating() {
        float sum = 0;
        for (Review review : reviews) {
            sum += review.getRating();
        }
        rating = sum / reviews.size();
    }
}
