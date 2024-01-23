package imdb.app.demo.controllers;

import imdb.app.demo.entities.AppUser;
import imdb.app.demo.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("imdb/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/login-info/{username}")
    public ResponseEntity<AppUser> loginInfo(@PathVariable String username) {
        return userService.loginInfo(username);
    }

}
