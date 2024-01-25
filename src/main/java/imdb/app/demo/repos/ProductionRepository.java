package imdb.app.demo.repos;

import imdb.app.demo.entities.entries.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductionRepository extends JpaRepository<Production, Integer> {
}
