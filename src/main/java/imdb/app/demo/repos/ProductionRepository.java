package imdb.app.demo.repos;

import imdb.app.demo.entities.Production;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.repository.CrudRepository;

public interface ProductionRepository extends CrudRepository<Production, Integer> {
}
