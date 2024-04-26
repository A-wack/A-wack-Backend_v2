package org.awack.domain.mail.authcode.adapter;

import lombok.RequiredArgsConstructor;
import org.awack.domain.mail.authcode.dao.AuthCodeRepository;
import org.awack.domain.mail.authcode.model.AuthCode;
import org.awack.domain.mail.authcode.port.DeleteAuthCodePort;
import org.awack.domain.mail.authcode.port.FindAuthCodePort;
import org.awack.domain.mail.authcode.port.SaveAuthCodePort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthCodeAdapter implements FindAuthCodePort, SaveAuthCodePort, DeleteAuthCodePort {
    private final AuthCodeRepository authCodeRepository;

    @Override
    public Optional<AuthCode> findByEmail(String email) {
        return authCodeRepository.findById(email);
    }

    @Override
    public AuthCode save(AuthCode authCode) {
        return authCodeRepository.save(authCode);
    }

    @Override
    public void deleteByEmail(String email) {
        authCodeRepository.deleteById(email);
    }

}
