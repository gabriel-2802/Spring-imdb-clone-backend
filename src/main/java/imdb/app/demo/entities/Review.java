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
    private Integer id;
    private String title;
    private String content;
    private Float rating;

    @ManyToOne
    @JoinColumn(name = "production_id")
    private Production production;


}
