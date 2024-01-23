package org.example.services;

import org.example.data.model.Art;
import org.example.data.repository.BuyerRepository;
import org.example.dto.request.RegisterRequest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
class BuyerServiceImplTest {

    @Test
    void testThatBuyerCanViewAllArt() {
        BuyerService mockBuyerService = mock(BuyerService.class);
        when(mockBuyerService.viewAllArt("olalale")).thenReturn(new ArrayList<>());
        BuyerServiceImpl buyerServiceImpl = new BuyerServiceImpl(mockBuyerService);
        List<Art> result = buyerServiceImpl.viewAllArt("olalale");
        assertTrue(result.isEmpty());
    }


}