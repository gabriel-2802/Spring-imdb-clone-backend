package imdb.app.demo.repos;

import imdb.app.demo.entities.users.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByAuthority(String authority);
}
