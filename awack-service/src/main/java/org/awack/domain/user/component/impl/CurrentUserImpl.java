package org.awack.domain.user.component.impl;

import lombok.RequiredArgsConstructor;
import org.awack.custom.UserNotFoundException;
import org.awack.domain.user.component.CurrentUser;
import org.awack.domain.user.model.UserEntity;
import org.awack.domain.user.port.FindUserPort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrentUserImpl implements CurrentUser {
    private final FindUserPort findUserPort;

    @Override
    public UserEntity get() {
        var authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        return findUser(authentication.getName());
    }

    private UserEntity findUser(String email) {
        return findUserPort.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }

}
