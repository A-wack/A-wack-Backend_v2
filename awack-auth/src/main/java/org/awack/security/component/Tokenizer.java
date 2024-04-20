package org.awack.security.component;

import org.springframework.security.core.Authentication;

public interface Tokenizer {
    String create(String email);
    boolean validate(String token);
    String reissue(String token);
    Authentication getAuthentication(String token);
    String extractToken(String token);
    String getSubject(String token);

}
