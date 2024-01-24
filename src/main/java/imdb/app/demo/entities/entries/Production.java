package imdb.app.demo.entities.entries;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @Column
    protected String title;
    protected String description;
    @ElementCollection
    protected List<String> actors;
    @ElementCollection
    protected List<String> genres;
    @ElementCollection
    protected List<String> directors;
    protected int duration;
    @Column
    protected int releaseYear;
    @Column
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
