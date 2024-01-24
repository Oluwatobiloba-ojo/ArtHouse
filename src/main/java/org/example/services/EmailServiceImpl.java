package org.example.services;

import org.apache.logging.log4j.message.SimpleMessage;
import org.example.dto.request.EmailRequest;
import org.example.dto.response.EmailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{
    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public EmailResponse sendMailMessage(EmailRequest emailRequest) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        try {
            simpleMailMessage.setFrom(emailRequest.getSenderEmail());
            simpleMailMessage.setTo(emailRequest.getReceiverEmail());
            simpleMailMessage.setSubject(emailRequest.getTitle());
            simpleMailMessage.setText(emailRequest.getMessage());
            javaMailSender.send(simpleMailMessage);
            return new EmailResponse("Mail sent successfully");
        }catch (Exception exception){
            return new EmailResponse("Mail sent out wrongly");
        }
    }
}
