package imdb.app.demo.repos;

import imdb.app.demo.entities.AppUser;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {
    Boolean existsByUsername(String username);
    AppUser findByUsername(String username);
}
