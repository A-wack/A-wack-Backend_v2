package org.awack.domain.currentdailysong.model;

import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@RedisHash(timeToLive = 60 * 60 * 10)
public class CurrentDailySong {

    @Id
    private Long id;

    @Indexed
    private String url;

    protected CurrentDailySong(Long id, String url) {
    }

    public static CurrentDailySong of(Long id, String url) {
        return new CurrentDailySong(id, url);
    }

}
