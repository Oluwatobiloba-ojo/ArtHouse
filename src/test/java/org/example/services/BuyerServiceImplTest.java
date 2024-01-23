package org.example.services;

import org.example.data.repository.ArtistRepository;
import org.example.data.repository.BuyerRepository;
import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;
import org.example.exceptions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
 @SpringBootTest
public class BuyerServiceImplTest {
     @Autowired
    private BuyerService buyerService;
    @Autowired
    private BuyerRepository buyerRepository;
    @AfterEach
    public void deleteBeforeTest(){
        buyerRepository.deleteAll();
    }
    @Test
    public  void  testThatAnArtist_CanRegisterWithWrongUsernameThrowsException(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("star girl8");
        registerRequest.setPassword("joy,1234");
        registerRequest.setEmail("joytim7277@gmail.com");
        assertThrows(InvalidUsernameException.class,()->buyerService.register(registerRequest));
    }
    @Test
    public void testWhenArtistRegisterWithWrongPasswordThrowsException(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("Precious");
        registerRequest.setPassword("ada");
        registerRequest.setEmail("joytim7277@gmail.com");
        assertThrows(InvalidPasswordException.class, ()->buyerService.register(registerRequest));
    }
    @Test
    public void testThatWhenArtistRegisterWithWrongEmailThrowsException(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("Precious");
        registerRequest.setPassword("OlaPrecious");
        registerRequest.setEmail("joyt");
        assertThrows(InvalidEmailException.class, ()->buyerService.register(registerRequest));
    }


    @Test
    public void testThatArtistCanRegisterAgainWithCorrectInfoReturnsArtistObjects(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("Precious");
        registerRequest.setPassword("OlaPrecious");
        registerRequest.setEmail("joy@gmail.com");
        buyerService.register(registerRequest);
        assertThrows(BuyerExistException.class,()->buyerService.register(registerRequest));
    }

    @Test
    public void testThatArtistCan_RegisterAndLogin(){
        RegisterRequest registerRequest = new RegisterRequest();
        LoginRequest loginRequest = new LoginRequest();
        registerRequest.setUsername("Sandra");
        registerRequest.setPassword("Olaoluwajohn");
        registerRequest.setEmail("joy@gmail.com");
        buyerService.register(registerRequest) ;
        loginRequest.setUsername("Sandra");
        loginRequest.setPassword("Olaoluwajohn");
        loginRequest.setEmail("joy@gmail.com");
        buyerService.login(loginRequest);
    }
}

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