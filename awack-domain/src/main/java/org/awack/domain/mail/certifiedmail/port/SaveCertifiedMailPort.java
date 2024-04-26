package org.awack.domain.mail.certifiedmail.port;

import org.awack.domain.mail.certifiedmail.model.CertifiedMail;

public interface SaveCertifiedMailPort {
    CertifiedMail save(CertifiedMail certifiedMail);

}
