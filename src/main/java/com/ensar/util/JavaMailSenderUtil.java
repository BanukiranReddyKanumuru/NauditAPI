package com.ensar.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import com.ensar.config.EnsarSecrets;

import java.util.Properties;

@Component
public class JavaMailSenderUtil {

    @Value("${EMAIL_HOST:}")
    private String emailHost;

    @Value("${EMAIL_PORT:}")
    private String emailPort;

    @Autowired
    EnsarSecrets ensarSecrets;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(emailHost);
        mailSender.setPort(587);

        mailSender.setUsername(ensarSecrets.getEmailUserName());
        mailSender.setPassword(ensarSecrets.getEmailUserPwd());


        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
