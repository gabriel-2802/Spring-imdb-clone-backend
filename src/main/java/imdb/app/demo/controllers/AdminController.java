package imdb.app.demo.controllers;

import imdb.app.demo.entities.users.AppUser;
import imdb.app.demo.entities.entries.Production;
import imdb.app.demo.entities.request_response.MovieRequest;
import imdb.app.demo.services.interfaces.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("imdb/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> showAllUsers() {
        return adminService.showAllUsers();
    }

    @GetMapping("/productions")
    public ResponseEntity<List<Production>> showAllProduction() {
        return adminService.showAllProduction();
    }

    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody MovieRequest production) {
        return adminService.addMovie(production);
    }

    @DeleteMapping("/delete-movie/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Integer id) {
        return adminService.deleteMovie(id);
    }

    @PutMapping("/update-movie/{id}")
    public ResponseEntity<String> updateMovie(@PathVariable Integer id, @RequestBody MovieRequest production) {
        return adminService.updateMovie(id, production);
    }

    @PostMapping("add-movies")
    public ResponseEntity<String> addMovies(@RequestBody List<MovieRequest> movies) {
        for (MovieRequest movie : movies) {
            adminService.addMovie(movie);
        }
        return ResponseEntity.ok("Movies added successfully");
    }

}
