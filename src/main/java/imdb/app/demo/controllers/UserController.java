package imdb.app.demo.controllers;

import imdb.app.demo.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("imdb/content")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

}
