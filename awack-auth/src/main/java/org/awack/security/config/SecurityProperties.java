package org.awack.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "awack.security")
public record SecurityProperties(
        String secret,
        String issuer,
        Long expiration,
        String algorithm
) {
}
