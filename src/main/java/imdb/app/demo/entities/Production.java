package imdb.app.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Production {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;
    @ElementCollection
    private List<String> actors;
    @ElementCollection
    private List<String> genres;
    @ElementCollection
    private List<String> directors;
    private int duration;
    private int releaseYear;

    private float rating;
    @OneToMany(mappedBy = "production", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;
    public abstract ProductionTypes getType();
    public void updateRating() {
        float sum = 0;
        for (Review review : reviews) {
            sum += review.getRating();
        }
        rating = sum / reviews.size();
    }
}
