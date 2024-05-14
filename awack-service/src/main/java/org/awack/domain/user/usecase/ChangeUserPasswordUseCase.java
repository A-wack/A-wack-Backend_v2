package org.awack.domain.user.usecase;

import org.awack.domain.user.dto.ChangeUserPasswordRequest;

public interface ChangeUserPasswordUseCase {
    void change(ChangeUserPasswordRequest request);

}
