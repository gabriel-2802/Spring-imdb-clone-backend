package imdb.app.demo.services.interfaces;

import imdb.app.demo.entities.AppUser;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<AppUser> loginInfo(String username);
}
