package org.example.services;

import org.example.data.repository.ArtistRepository;
import org.example.dto.request.FindAArtRequest;
import org.example.dto.request.RemoveAArtRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ArtistServiceTest {
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private ArtistService artistService;

    @Test
    public void testThatArtistCanFindTheArtThatBelongToThem(){
        assertEquals(3,artistService.findArtsBelongingTo("ArtHouse123@gmail.com"));

    }
    @Test
    public void testThatUserCanFindAArtBelongToTheUser(){
        FindAArtRequest findAArtRequest = new FindAArtRequest();
        findAArtRequest.setEmail("ArtHouse123@gmail.com");
        findAArtRequest.setArtId(1L);
        assertNotNull(artistService.findAArt(findAArtRequest));
    }
    @Test
    public void testThatUserCanRemoveArtBelongingToUser(){
        RemoveAArtRequest removeAArtRequest = new RemoveAArtRequest();
        removeAArtRequest.setEmail("ArtHouse123@gmail.com");
        removeAArtRequest.setArtId(1L);
        artistService.removeAArt(removeAArtRequest);
        FindAArtRequest findAArtRequest = new FindAArtRequest();
        findAArtRequest.setEmail("ArtHouse123@gmail.com");
        findAArtRequest.setArtId(1L);
        assertNull(artistService.findAArt(findAArtRequest));


    }

}