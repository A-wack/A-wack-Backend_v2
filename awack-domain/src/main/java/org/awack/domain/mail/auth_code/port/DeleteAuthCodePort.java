package org.awack.domain.mail.auth_code.port;

public interface DeleteAuthCodePort {
    void deleteByEmail(String email);

}
