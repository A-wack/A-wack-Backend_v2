package org.awack.domain.mail.usecase;

import org.awack.domain.mail.certified_mail.dto.ConfirmMailRequest;

public interface ConfirmMailUseCase {
    void confirm(ConfirmMailRequest request);

}
