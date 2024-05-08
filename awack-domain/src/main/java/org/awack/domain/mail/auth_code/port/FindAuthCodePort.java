package org.awack.domain.mail.auth_code.port;

import org.awack.domain.mail.auth_code.model.AuthCode;

import java.util.Optional;

public interface FindAuthCodePort {
    Optional<AuthCode> findByEmail(String email);

}
