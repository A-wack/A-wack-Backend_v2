package org.awack.domain.mail.authcode.port;

public interface DeleteAuthCodePort {
    void deleteByEmail(String email);

}
