package org.awack.domain.mail.auth_code.port;

import org.awack.domain.mail.auth_code.model.AuthCode;

public interface SaveAuthCodePort {
    AuthCode save(AuthCode authCode);

}
