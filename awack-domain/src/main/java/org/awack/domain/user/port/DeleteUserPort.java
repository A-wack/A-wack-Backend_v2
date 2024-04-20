package org.awack.domain.user.port;

import org.awack.domain.user.model.UserEntity;

public interface DeleteUserPort {
    void delete(UserEntity userEntity);

}
