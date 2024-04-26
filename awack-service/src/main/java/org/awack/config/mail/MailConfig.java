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
        javaMailSender.setUsername(mailProperties.getEmail());
        javaMailSender.setPassword(mailProperties.getPassword());
        javaMailSender.setPort(mailProperties.getPort());
        javaMailSender.setJavaMailProperties(getMailProperties());
        javaMailSender.setDefaultEncoding("UTF-8");

        return javaMailSender;
    }

    private Properties getMailProperties() {
        Properties pt = new Properties();

        pt.put("mail.smtp.socketFactory.port", mailProperties.getPort());
        pt.put("mail.smtp.auth", mailProperties.isAuth());
        pt.put("mail.smtp.starttls.enable", mailProperties.isStarttlsEnable());
        pt.put("mail.smtp.starttls.required", mailProperties.isStarttlsRequired());
        pt.put("mail.smtp.socketFactory.fallback", mailProperties.isSocketFactoryFallback());
        pt.put("mail.smtp.socketFactory.class", mailProperties.getSocketFactoryClass());
        pt.put("mail.smtp.ssl.checkserveridentity", "true");

        return pt;
    }

}
