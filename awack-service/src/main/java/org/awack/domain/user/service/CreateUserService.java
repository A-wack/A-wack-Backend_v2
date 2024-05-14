package org.awack.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.awack.custom.UnverifiedEmailException;
import org.awack.custom.UserAlreadyExistsByEmailException;
import org.awack.domain.mail.certified_mail.port.FindCertifiedMailPort;
import org.awack.domain.user.dto.CreateUserRequest;
import org.awack.domain.user.port.FindUserPort;
import org.awack.domain.user.port.SaveUserPort;
import org.awack.domain.user.usecase.CreateUserUseCase;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateUserService implements CreateUserUseCase {
    private final FindCertifiedMailPort findCertifiedMailPort;
    private final PasswordEncoder passwordEncoder;
    private final SaveUserPort saveUserPort;
    private final FindUserPort findUserPort;

    @Override
    public void create(CreateUserRequest request) {
        validateUserExists(request.email());

        saveUserPort.save(
                CreateUserRequest.toEntity(
                        request,
                        passwordEncoder.encode(request.password())
                )
        );
    }

    private void validateUserExists(String email) {
        if (findUserPort.exists(email)) {
            throw new UserAlreadyExistsByEmailException();
        }
        if (!findCertifiedMailPort.exists(email)) {
            throw new UnverifiedEmailException();
        }
    }

}
