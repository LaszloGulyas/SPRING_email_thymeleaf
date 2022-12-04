package com.laca.springmail.service;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {

    private final EmailService emailService;

    public EmailSender(EmailService emailService) {
        this.emailService = emailService;
    }

    @Bean
    public void sendMyEmail() {
        String from = "dummy";
        String to = "dummy";
        String subject = "dummy";

        emailService.sendThTemplateBasic(from, to, subject);
    }
}
