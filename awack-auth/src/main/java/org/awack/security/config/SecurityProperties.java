package org.awack.security.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "awack.security")
public class SecurityProperties {
    private String secret;
    private String issuer;
    private long expiration;
    private String algorithm;

}
