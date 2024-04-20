package org.awack.domain.user.adapter;

import lombok.RequiredArgsConstructor;
import org.awack.domain.user.dao.UserRepository;
import org.awack.domain.user.model.UserEntity;
import org.awack.domain.user.port.DeleteUserPort;
import org.awack.domain.user.port.FindUserPort;
import org.awack.domain.user.port.SaveUserPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserAdapter implements FindUserPort, SaveUserPort, DeleteUserPort {
    private final UserRepository userRepository;

    @Override
    public void delete(UserEntity userEntity) {
        userRepository.delete(userEntity);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean exists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

}
