package org.awack.domain.dailysong_history.model;

import jakarta.persistence.*;
import lombok.Getter;
import org.awack.domain.user.model.UserEntity;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Table(name = "daily_song_history")
@Entity(name = "daily_song_history")
public class DailySongHistory {

    @Id
    private Long id;

    private String url;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    protected DailySongHistory() {}

    private DailySongHistory(Long id, String url, UserEntity user) {
        this.id = id;
        this.url = url;
        this.user = user;
    }

    public static DailySongHistory of(Long id, String url, UserEntity user) {
        return new DailySongHistory(id, url, user);
    }

}
