package org.awack.domain.user.usecase;

import org.awack.domain.user.dto.CreateUserRequest;

public interface CreateUserUseCase {
    void create(CreateUserRequest request);

}
