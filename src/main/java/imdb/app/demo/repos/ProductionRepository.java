package imdb.app.demo.repos;

import imdb.app.demo.entities.entries.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

import static org.hibernate.grammars.hql.HqlParser.FROM;

public interface ProductionRepository extends JpaRepository<Production, Integer> {

    @Query(value = "SELECT p FROM Production p ORDER BY p.rating DESC")
    List<Production> findTopRated();

    @Query("SELECT p FROM Production p ORDER BY function('random')")
    List<Production> findRandom();

    @Query("SELECT p FROM Production p ORDER BY p.releaseYear DESC")
    List<Production> findLatest();
}
