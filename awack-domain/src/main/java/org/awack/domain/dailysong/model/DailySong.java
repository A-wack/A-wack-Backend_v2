package org.awack.domain.dailysong.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.awack.domain.user.model.UserEntity;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Table(name = "song_request")
@Entity(name = "song_request")
public class DailySong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private DailySongStatus status = DailySongStatus.PENDING;

    protected DailySong() {}

    public DailySong(String url, UserEntity user) {
        this.url = url;
        this.user = user;
    }

    public void cancel() {
        status = DailySongStatus.CANCELED;
    }

    public void adopt() {
        status = DailySongStatus.ADOPTED;
    }

}
