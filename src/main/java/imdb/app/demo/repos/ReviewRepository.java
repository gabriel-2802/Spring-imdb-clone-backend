package imdb.app.demo.repos;

import imdb.app.demo.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
     boolean existsByProductionIdAndUserUsername(Integer productionId, String  username);
     List<Review>  findAllByUserUsername(String username);
}
