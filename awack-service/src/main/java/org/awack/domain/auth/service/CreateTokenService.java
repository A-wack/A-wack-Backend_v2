package org.awack.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.awack.custom.PasswordNotMatchesException;
import org.awack.custom.UserNotFoundException;
import org.awack.domain.auth.dto.CreateTokenRequest;
import org.awack.domain.auth.dto.CreateTokenResponse;
import org.awack.domain.auth.usecase.CreateTokenUseCase;
import org.awack.domain.user.model.UserEntity;
import org.awack.domain.user.port.FindUserPort;
import org.awack.security.component.Tokenizer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTokenService implements CreateTokenUseCase {
    private final PasswordEncoder passwordEncoder;
    private final FindUserPort findUserPort;
    private final Tokenizer tokenizer;

    @Override
    public CreateTokenResponse create(final CreateTokenRequest request) {
        validate(request);

        return CreateTokenResponse.of(
                tokenizer.create(
                        request.email()
                )
        );
    }

    @Override
    public CreateTokenResponse reissue(String token) {
        return CreateTokenResponse.of(
                tokenParse(
                        tokenizer.reissue(
                                token
                        )
                )
        );
    }

    private String tokenParse(String token) {
        if (token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return token;
    }

    private void validate(final CreateTokenRequest request) {
        final UserEntity user = findUserPort.findByEmail(request.email())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw PasswordNotMatchesException.EXCEPTION;
        }
    }

}
