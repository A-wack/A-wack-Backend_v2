package org.awack.domain.user.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.awack.domain.user.model.UserEntity;

public record CreateUserRequest(
        @Size(min = 1, max = 5,
                message = "이름은 1자 이상, 5자 이하로 입력해야합니다.")
        String name,
        @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(dsm\\.hs\\.kr)$",
                message = "@dsm.hs.kr 형식의 이메일을 입력해야합니다.")
        String email,
        @Size(min = 8, max = 30,
                message = "비밀번호는 8자 이상, 30자 이하로 입력해야합니다.")
        String password
) {

        public static UserEntity toEntity(CreateUserRequest request, String password) {
                return UserEntity.builder()
                        .name(request.name())
                        .email(request.email())
                        .password(password)
                        .build();
        }

}
