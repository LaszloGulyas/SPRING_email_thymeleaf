package com.laca.springmail.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;


@Configuration
@PropertySource("classpath:emailconfig.properties")
public class EmailConfig {

    @Value("${EMAIL_HOST}")
    private String host;

    @Value("${EMAIL_PORT}")
    private Integer port;

    @Value("${EMAIL_USERNAME}")
    private String username;

    @Value("${EMAIL_PASSWORD}")
    private String password;

    @Value("${EMAIL_SMTP_AUTH}")
    private Boolean smtpAuth;

    @Value("${EMAIL_SMTP_TIMEOUT}")
    private Integer smtpTimeout;

    @Value("${EMAIL_SMTP_CONNECTION_TIMEOUT}")
    private Integer smtpConnectionTimeout;

    @Value("${EMAIL_SMTP_WRITE_TIMEOUT}")
    private Integer smtpWriteTimeout;

    @Value("${EMAIL_SMTP_STARTTLS}")
    private Boolean smtpStartTls;


    @Bean
    public JavaMailSender mailSender() {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.smtp.auth", smtpAuth);
        properties.put("mail.smtp.timeout", smtpTimeout);
        properties.put("mail.smtp.connectiontimeout", smtpConnectionTimeout);
        properties.put("mail.smtp.writetimeout", smtpWriteTimeout);

        properties.put("mail.smtp.starttls.enable", smtpStartTls);

        return mailSender;
    }
}
