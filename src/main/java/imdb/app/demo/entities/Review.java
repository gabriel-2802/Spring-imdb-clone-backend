package imdb.app.demo.entities;

import imdb.app.demo.entities.entries.Production;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@EntityListeners(ReviewListener.class)
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
    private Production production;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;
}
