package org.awack.domain.mail.service;

import lombok.RequiredArgsConstructor;
import org.awack.custom.InvalidAuthCodeException;
import org.awack.domain.mail.auth_code.model.AuthCode;
import org.awack.domain.mail.auth_code.port.DeleteAuthCodePort;
import org.awack.domain.mail.auth_code.port.FindAuthCodePort;
import org.awack.domain.mail.certified_mail.dto.ConfirmMailRequest;
import org.awack.domain.mail.certified_mail.model.CertifiedMail;
import org.awack.domain.mail.certified_mail.port.SaveCertifiedMailPort;
import org.awack.domain.mail.usecase.ConfirmMailUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ConfirmMailService implements ConfirmMailUseCase {
    private final SaveCertifiedMailPort saveCertifiedMailPort;
    private final DeleteAuthCodePort deleteAuthCodePort;
    private final FindAuthCodePort findAuthCodePort;

    @Override
    public void confirm(ConfirmMailRequest request) {
        var authCode = findAuthCodePort.findByEmail(request.email())
                .orElseThrow(InvalidAuthCodeException::new);

        if (!authCode.getCode().equals(request.code())) {
            throw new InvalidAuthCodeException();
        }

        saveCertifiedMail(request.email());
        deleteAuthCode(request.email());
    }

    private void saveCertifiedMail(String email) {
        saveCertifiedMailPort.save(
                CertifiedMail.of(
                        email
                )
        );
    }

    private void deleteAuthCode(String email) {
        deleteAuthCodePort.deleteByEmail(email);
    }

}
