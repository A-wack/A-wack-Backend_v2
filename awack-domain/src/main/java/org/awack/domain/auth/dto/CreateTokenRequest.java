package org.awack.domain.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateTokenRequest(
        @NotBlank(message = "이메일을 입력해주세요.")
        String email,
        @NotBlank(message = "비밀번호을 입력해주세요.")
        String password
) {
}
