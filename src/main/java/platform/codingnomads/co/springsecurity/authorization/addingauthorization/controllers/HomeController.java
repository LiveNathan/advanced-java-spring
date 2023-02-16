package platform.codingnomads.co.springsecurity.authorization.addingauthorization.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.security.RolesAllowed;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage() {
        return "authorization/home";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "authorization/admin";
    }

    @GetMapping("/superu")
    public String superUPage() {
        return "authorization/superu";
    }

    @GetMapping("/mas")
    @PreAuthorize("#id != 1")
    public String testMas(int id) {
        return "authorization/home";
    }

    // create at least 3 new endpoints, each accessible by a single role.
    // Add a link to each of these endpoints in your header.html template.
    // Run the application again and ensure your expected behavior. Good job!
    @GetMapping("/banana")
    public String bananaPage() {
        return "authorization/banana";
    }

    @RolesAllowed("SUPER_USER")
    @GetMapping("/apple")
    public String applePage() {
        return "authorization/apple";
    }

    @GetMapping("/orange")
    public String orangePage() {
        return "authorization/orange";
    }
    /*
        Method Security Annotations

        @RolesAllowed("USER")
        @PreAuthorize("#id != 1")
        @PostAuthorize("returnObject.ownerUsername == authentication.principal.username")
        @PreFilter(value = "filterObject != shutdown", filterTarget = "commands")
        @PostFilter("filterObject.id <= 20")
     */
}