package org.example.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BuyerServiceImplTest {
    @Autowired
    BuyerService buyerService;
    @Test
    public void testThatBuyerCanViewAllArtThatArePublished(){
        assertEquals(3,buyerService.viewAllPublishedArt("ArtHouse123@gmail.com").size());

    }


}