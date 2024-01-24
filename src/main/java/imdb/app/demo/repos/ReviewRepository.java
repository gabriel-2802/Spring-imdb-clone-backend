package imdb.app.demo.repos;

import imdb.app.demo.entities.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer> {
     boolean findByProductionIdAndUserUsername(Integer productionId, String username);
}
