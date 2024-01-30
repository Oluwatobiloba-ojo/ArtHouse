package org.example.controller;

import org.example.data.model.Art;
import org.example.dto.request.DisplayArtRequest;
import org.example.dto.request.FindAArtRequest;
import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;
import org.example.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {
    @Autowired
    private  ArtistService artistService;

    @PostMapping("/register")
    public ResponseEntity<String> register( @RequestBody RegisterRequest registerRequest) {
        try {
            artistService.register(registerRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Registration successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login( @RequestBody LoginRequest loginRequest) {
        try {
            artistService.login(loginRequest);
            return ResponseEntity.ok("Login successful");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed: " + exception.getMessage());
        }
    }

    @PostMapping("/displayArt")
    public ResponseEntity<String> displayArt(@RequestBody DisplayArtRequest displayArtRequest) {
        try {
            artistService.displayArt(displayArtRequest);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Art displayed successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to display art: " + e.getMessage());
        }
    }

    @GetMapping("/viewAllArt")
    public ResponseEntity<List<Art>> viewAllArt(@RequestParam String username, @RequestParam String email) {
        List<Art> artList = artistService.findAllArt(username, email);
        return ResponseEntity.ok(artList);
    }

    @GetMapping("/findArt")
    public ResponseEntity<Art> findArt(@ModelAttribute FindAArtRequest findAArtRequest) {
        try {
            Art foundArt = artistService.findAArt(findAArtRequest);
            return ResponseEntity.ok(foundArt);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}


