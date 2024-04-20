package org.awack.domain.user.port;

import org.awack.domain.user.model.UserEntity;

import java.util.Optional;

public interface FindUserPort {
    Optional<UserEntity> findByEmail(String email);
    boolean exists(String email);

}
