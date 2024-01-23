package imdb.app.demo.services;

import imdb.app.demo.entities.AppUser;
import imdb.app.demo.repos.MovieRepository;
import imdb.app.demo.repos.ProductionRepository;
import imdb.app.demo.repos.UserRepository;
import imdb.app.demo.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceV1 implements UserService {
    private final UserRepository userRepository;
    private final ProductionRepository productionRepository;
    private final MovieRepository movieRepository;

    @Override
    public ResponseEntity<AppUser> loginInfo(String username) {
        if (!userRepository.existsByUsername(username)) {
            return ResponseEntity.notFound().build();
        }

        AppUser user = userRepository.findByUsername(username);
        return ResponseEntity.ok(user);
    }
}
