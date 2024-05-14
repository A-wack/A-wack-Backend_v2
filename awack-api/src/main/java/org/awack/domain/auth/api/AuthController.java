package org.awack.domain.auth.api;

import lombok.RequiredArgsConstructor;
import org.awack.domain.auth.dto.CreateTokenRequest;
import org.awack.domain.auth.dto.CreateTokenResponse;
import org.awack.domain.auth.usecase.CreateTokenUseCase;
import org.awack.domain.user.dto.CreateUserRequest;
import org.awack.domain.user.usecase.CreateUserUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final CreateTokenUseCase createTokenUseCase;
    private final CreateUserUseCase createUserUseCase;

    @PostMapping("/login")
    public CreateTokenResponse login(@RequestBody CreateTokenRequest request) {
        return createTokenUseCase.create(request);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody CreateUserRequest request) {
        createUserUseCase.create(request);
    }

    @PutMapping("/reissue")
    public CreateTokenResponse reissue(@RequestHeader("Authorization") String token) {
        return createTokenUseCase.reissue(token);
    }

}
