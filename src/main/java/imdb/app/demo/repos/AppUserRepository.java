package imdb.app.demo.repos;

import imdb.app.demo.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByUsername(String username);
    Boolean existsAppUserByUsername(String username);
}
