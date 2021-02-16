package com.laca.springmail.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


import java.util.Objects;
import java.util.Properties;


@Configuration
@PropertySource(value = "classpath:emailconfig.properties")
public class EmailConfig   {

    private final Environment environment;

    @Autowired
    public EmailConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public JavaMailSender mailSender() {

        final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(this.environment.getProperty("EMAIL_HOST"));
        mailSender.setPort(Integer.parseInt(Objects.requireNonNull(this.environment.getProperty("EMAIL_PORT"))));
        mailSender.setUsername(this.environment.getProperty("EMAIL_USERNAME"));
        mailSender.setPassword(this.environment.getProperty("EMAIL_PASSWORD"));

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.smtp.auth", this.environment.getProperty("EMAIL_SMTP_AUTH"));
        properties.put("mail.smtp.timeout", this.environment.getProperty("EMAIL_SMTP_TIMEOUT"));
        properties.put("mail.smtp.connectiontimeout", this.environment.getProperty("EMAIL_SMTP_CONNECTION_TIMEOUT"));
        properties.put("mail.smtp.writetimeout", this.environment.getProperty("EMAIL_SMTP_WRITE_TIMEOUT"));

        return mailSender;
    }
}
