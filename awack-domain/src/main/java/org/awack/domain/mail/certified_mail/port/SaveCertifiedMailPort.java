package org.awack.domain.mail.certified_mail.port;

import org.awack.domain.mail.certified_mail.model.CertifiedMail;

public interface SaveCertifiedMailPort {
    CertifiedMail save(CertifiedMail certifiedMail);

}
