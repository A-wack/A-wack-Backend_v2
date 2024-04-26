package org.awack.config.mail;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "mail")
public class MailProperties {

    private String email;
    private String password;
    private boolean auth;
    private boolean starttlsRequired;
    private boolean starttlsEnable;
    private String socketFactoryClass;
    private boolean socketFactoryFallback;
    private int port;

}
