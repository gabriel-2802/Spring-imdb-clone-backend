package imdb.app.demo.controllers;

import imdb.app.demo.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("imdb/content")
@RequiredArgsConstructor
public class GeneralAccessController {

    @GetMapping("/home")
    public String home() {
        return "Welcome to Gabriel's IMDB";
    }

    // TODO
}
