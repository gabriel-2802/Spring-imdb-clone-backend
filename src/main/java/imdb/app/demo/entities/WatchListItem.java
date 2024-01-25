package imdb.app.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import imdb.app.demo.entities.entries.Production;
import imdb.app.demo.entities.users.AppUser;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JoinColumnOrFormula;

@Entity
@Data
public class WatchListItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "production_id")
    private Production production;

    public WatchListItem() {
    }

    public WatchListItem(AppUser user, Production production) {
        this.user = user;
        this.production = production;
    }
}
