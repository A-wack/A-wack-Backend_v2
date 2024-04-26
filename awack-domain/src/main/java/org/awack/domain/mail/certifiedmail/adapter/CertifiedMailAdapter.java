package org.awack.domain.mail.certifiedmail.adapter;

import lombok.RequiredArgsConstructor;
import org.awack.domain.mail.certifiedmail.dao.CertifiedMailRepository;
import org.awack.domain.mail.certifiedmail.model.CertifiedMail;
import org.awack.domain.mail.certifiedmail.port.DeleteCertifiedMailPort;
import org.awack.domain.mail.certifiedmail.port.FindCertifiedMailPort;
import org.awack.domain.mail.certifiedmail.port.SaveCertifiedMailPort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CertifiedMailAdapter implements FindCertifiedMailPort, SaveCertifiedMailPort, DeleteCertifiedMailPort {
    private final CertifiedMailRepository certifiedMailRepository;

    @Override
    public void delete(String email) {
        certifiedMailRepository.deleteById(email);
    }

    @Override
    public boolean exists(String email) {
        return certifiedMailRepository.existsById(email);
    }

    @Override
    public CertifiedMail save(CertifiedMail certifiedMail) {
        return certifiedMailRepository.save(certifiedMail);
    }

}
