package org.awack.config.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class MailConfig {
    private final MailProperties mailProperties;

    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setUsername(mailProperties.email());
        javaMailSender.setPassword(mailProperties.password());
        javaMailSender.setPort(mailProperties.port());
        javaMailSender.setJavaMailProperties(getMailProperties());
        javaMailSender.setDefaultEncoding("UTF-8");

        return javaMailSender;
    }

    private Properties getMailProperties() {
        Properties pt = new Properties();

        pt.put("mail.smtp.socketFactory.port", mailProperties.port());
        pt.put("mail.smtp.auth", mailProperties.auth());
        pt.put("mail.smtp.starttls.enable", mailProperties.starttlsEnable());
        pt.put("mail.smtp.starttls.required", mailProperties.starttlsRequired());
        pt.put("mail.smtp.socketFactory.fallback", mailProperties.socketFactoryFallback());
        pt.put("mail.smtp.socketFactory.class", mailProperties.socketFactoryClass());
        pt.put("mail.smtp.ssl.checkserveridentity", "true");

        return pt;
    }

}
