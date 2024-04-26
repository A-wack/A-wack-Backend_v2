package org.awack.domain.mail.authcode.port;

import org.awack.domain.mail.authcode.model.AuthCode;

import java.util.Optional;

public interface FindAuthCodePort {
    Optional<AuthCode> findByEmail(String email);

}
