package org.example.services;

import org.example.dto.request.EmailRequest;
import org.example.dto.response.EmailResponse;

public interface EmailService {
    EmailResponse sendMailMessage(EmailRequest emailRequest);
}
