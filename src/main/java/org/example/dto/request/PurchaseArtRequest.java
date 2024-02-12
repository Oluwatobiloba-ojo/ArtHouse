package org.example.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PurchaseArtRequest {
    private Long artId;
    private String buyerUsername;
    private BigDecimal amount;
    private String buyerEmail;
}
