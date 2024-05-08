package org.awack.domain.current_dailysong.model;

import jakarta.persistence.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash(timeToLive = 60 * 60 * 10)
public record CurrentDailySong(
        @Id Long id,
        @Indexed String url
) {
    public static CurrentDailySong of(Long id, String url) {
        return new CurrentDailySong(id, url);
    }
}
