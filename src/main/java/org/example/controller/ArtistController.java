package org.example.controller;

import org.example.data.model.Art;
import org.example.dto.request.*;
import org.example.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artist")
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
    public ResponseEntity<Art> displayArt(@RequestBody DisplayArtRequest displayArtRequest) {
        try {
            return new ResponseEntity<>(artistService.displayArt(displayArtRequest), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            e.fillInStackTrace();
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/viewAllArt")
    public ResponseEntity<List<Art>> viewAllArt(@RequestBody ViewAllArt viewAllArt) {
        try {
            return new ResponseEntity<>(artistService.findAllArt(viewAllArt.getUsername(), viewAllArt.getEmail()), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            e.fillInStackTrace();
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findArt")
    public ResponseEntity<Art> findArt(@RequestBody FindAArtRequest findAArtRequest) {
        try {
            Art foundArt = artistService.findAArt(findAArtRequest);
            return new ResponseEntity<>(foundArt, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            e.fillInStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}


