package imdb.app.demo.entities.entries;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import imdb.app.demo.entities.Review;
import imdb.app.demo.entities.WatchListItem;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        float sum = 0;;
        try {
            rating = reviews.stream().reduce(sum, (acc, review) -> acc + review.getRating(), Float::sum)
                    / reviews.size();
        } catch (Exception e) {
            rating = 0;
            reviews = new ArrayList<>();
        }
    }

    @OneToMany(mappedBy = "production", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<WatchListItem> watchListItems = new HashSet<>();
}
