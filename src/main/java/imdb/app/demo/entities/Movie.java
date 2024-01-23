package imdb.app.demo.entities;

import jakarta.persistence.Entity;

@Entity
public class Movie extends Production {
    @Override
    public ProductionTypes getType() {
        return ProductionTypes.MOVIE;
    }
}
