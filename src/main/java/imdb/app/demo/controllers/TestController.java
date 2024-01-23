package imdb.app.demo.controllers;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/imdb")
public class TestController {

    // Endpoint accessible only by admins
    @GetMapping("/admin/test")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String adminTest() {
        return "Admin content access granted";
    }


    // Endpoint accessible by any authenticated user
    @GetMapping("/common/test")
    public String commonTest() {
        return "Common content accessible by any authenticated user";
    }
}
