package org.awack.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.awack.domain.user.component.CurrentUser;
import org.awack.domain.user.port.DeleteUserPort;
import org.awack.domain.user.usecase.DeleteUserUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteUserService implements DeleteUserUseCase {
    private final DeleteUserPort deleteUserPort;
    private final CurrentUser currentUser;

    @Override
    public void delete() {
        deleteUserPort.delete(
                currentUser.get()
        );
    }

}
