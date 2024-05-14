package org.awack.domain.mail.usecase;

import org.awack.domain.mail.auth_code.dto.SendMailRequest;

import java.io.UnsupportedEncodingException;

public interface SendMailUseCase {
    void send(SendMailRequest request) throws UnsupportedEncodingException;

}
