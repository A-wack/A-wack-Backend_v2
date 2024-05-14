package org.awack.config.mail;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "mail")
public record MailProperties(
        String email,
        String password,
        Boolean auth,
        Boolean starttlsRequired,
        Boolean starttlsEnable,
        String socketFactoryClass,
        Boolean socketFactoryFallback,
        Integer port
) {
}
