package org.example.dto.request;

import lombok.Data;

@Data
public class EmailRequest {
    private String senderEmail;
    private String receiverEmail;
    private String title;
    private String message;
}
