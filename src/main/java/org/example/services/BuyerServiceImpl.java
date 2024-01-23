package org.example.services;

import org.example.data.model.Art;
import org.example.data.model.Buyer;
import org.example.data.repository.BuyerRepository;
import org.example.dto.request.FindByEmail;
import org.example.dto.request.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuyerServiceImpl implements BuyerService {

    private final List<RegisterRequest> registerRequests = new ArrayList<>();
    private final FindByEmail findByEmail = new FindByEmail();
private final BuyerService buyerService;
    @Autowired
    public BuyerServiceImpl(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @Override
    public List<Art> viewAllArt(String email) {
        RegisterRequest registerRequest = getRegisterRequestByEmail(email);

        if (registerRequest != null && registerRequest.getUsername().equals(email)) {
            Buyer buyer = getBuyerByEmail(email);
            if (buyer != null && buyer.isEnable()) {
                return buyerService.viewAllArt(email);
            }
        }

        return List.of();
    }

    private RegisterRequest getRegisterRequestByEmail(String email) {
        for (RegisterRequest request : registerRequests) {
            if (request.getEmail().equals(email)) {
                return request;
            }
        }
        return null;
    }


    private Buyer getBuyerByEmail(String email) {
        return (Buyer) findByEmail.findByEmail(email);
    }
}
