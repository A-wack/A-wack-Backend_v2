package org.awack.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.awack.custom.UnverifiedEmailException;
import org.awack.custom.UserNotFoundException;
import org.awack.domain.mail.certified_mail.port.FindCertifiedMailPort;
import org.awack.domain.user.dto.ChangeUserPasswordRequest;
import org.awack.domain.user.model.UserEntity;
import org.awack.domain.user.port.FindUserPort;
import org.awack.domain.user.usecase.ChangeUserPasswordUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ChangeUserPasswordService implements ChangeUserPasswordUseCase {
    private final FindCertifiedMailPort findCertifiedMailPort;
    private final FindUserPort findUserPort;

    @Override
    public void change(ChangeUserPasswordRequest request) {
        validateUserByEmail(request.email());

        var user = findUserPort.findByEmail(request.email())
                .orElseThrow(UserNotFoundException::new);

        user.changePassword(request.password());
    }

    private void validateUserByEmail(String email) {
        if (!findCertifiedMailPort.exists(email)) {
            throw new UnverifiedEmailException();
        }
    }

}
