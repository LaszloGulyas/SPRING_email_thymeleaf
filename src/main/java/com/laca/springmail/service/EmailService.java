package com.laca.springmail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
@Component
public class EmailService {

    private final MailSender mailSender;
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Autowired
    public EmailService(MailSender mailSender, JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    //basic email sender solution
    public void sendEmail(String from, String to, String subject, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(from);
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(text);

        try {
            this.mailSender.send(msg);
        } catch (MailException e) {
            System.out.println(e.getMessage());
        }
    }

    //mime supported enhanced email sender solution
    public void sendMimeEmail(String from, String to, String subject, String htmlText) {
        MimeMessage msg = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);

            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlText, true);

            javaMailSender.send(msg);
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        }

    }

    public void sendThTemplateBasic(String from, String to, String subject) {
        //customize the method parameters according to the desired variables shown on html template

        Context context = new Context();
        //context.setVariable("...", "...");

        //update the template name according to the used html template in resources/templates folder
        String htmlTextBody = templateEngine.process("EmailTemplateBasic", context);
        sendMimeEmail(from, to, subject, htmlTextBody);
    }
}
