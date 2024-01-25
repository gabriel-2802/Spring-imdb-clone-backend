package imdb.app.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import imdb.app.demo.entities.entries.Production;
import imdb.app.demo.entities.users.AppUser;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "reviews")
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Integer id;
    @Column(name = "review_title")
    private String title;
    private String content;
    @Column(name = "review_rating")
    private Float rating;

    @ManyToOne
    @JoinColumn(name = "production_id")
    @JsonIgnore
    private Production production;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private AppUser user;
    @JsonIgnore
    @Column(nullable = true)
    private Integer prodId;

    public Review() {
    }

    public Review(String title, String content, Float rating, Production production, AppUser user) {
        this.title = title;
        this.content = content;
        this.rating = rating;
        this.production = production;
        this.user = user;
        prodId = production.getId();
    }

    @JsonProperty("reviewer")
    public String getReviewer() {
        return user != null ? user.getUsername() : "Anonymous";
    }

    @JsonProperty("productionTitle")
    public String getProductionTitle() {
        return production != null ? production.getTitle() : "Unknown";
    }
}
