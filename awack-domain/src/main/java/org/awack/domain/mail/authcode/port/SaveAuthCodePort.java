package org.awack.domain.mail.authcode.port;

import org.awack.domain.mail.authcode.model.AuthCode;

public interface SaveAuthCodePort {
    AuthCode save(AuthCode authCode);

}
