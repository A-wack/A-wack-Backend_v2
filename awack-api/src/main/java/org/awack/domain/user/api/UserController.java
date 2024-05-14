package org.awack.domain.user.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.awack.domain.user.dto.ChangeUserPasswordRequest;
import org.awack.domain.user.usecase.ChangeUserPasswordUseCase;
import org.awack.domain.user.usecase.DeleteUserUseCase;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final ChangeUserPasswordUseCase changeUserPasswordUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @PatchMapping("/password")
    public void changePassword(@Valid @RequestBody ChangeUserPasswordRequest request) {
        changeUserPasswordUseCase.change(request);
    }

}
