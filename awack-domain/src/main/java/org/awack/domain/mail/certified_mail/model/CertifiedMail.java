package org.awack.domain.mail.certified_mail.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(timeToLive = 2 * 60 * 60)
public class CertifiedMail {

    @Id
    private String email;

    protected CertifiedMail(String email) {
        this.email = email;
    }

    public static CertifiedMail of(String email) {
        return new CertifiedMail(email);
    }

}
