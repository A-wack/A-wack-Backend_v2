package org.awack.domain.mail.auth_code.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@RedisHash(timeToLive = 2 * 60 * 60)
public class AuthCode {

    @Id
    private String email;

    @Indexed
    private String code;

    protected AuthCode(String email, String code) {
        this.email = email;
        this.code = code;
    }

    public static AuthCode of(String email, String code) {
        return new AuthCode(email, code);
    }

}
