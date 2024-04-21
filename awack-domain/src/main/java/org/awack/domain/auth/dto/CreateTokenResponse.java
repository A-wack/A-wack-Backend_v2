package org.awack.domain.auth.dto;

public record CreateTokenResponse(
        String token
) {

    public static CreateTokenResponse of(String token) {
        return new CreateTokenResponse(token);
    }

}
