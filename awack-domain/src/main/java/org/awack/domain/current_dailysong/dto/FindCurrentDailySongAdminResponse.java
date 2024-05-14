package org.awack.domain.current_dailysong.dto;

import org.awack.domain.user.dto.UserDetail;

import java.time.LocalDateTime;

public record FindCurrentDailySongAdminResponse(
        Long id,
        String url,
        LocalDateTime at,
        UserDetail user
) {

    public static FindCurrentDailySongAdminResponse of(Long id, String url, LocalDateTime at, UserDetail user) {
        return new FindCurrentDailySongAdminResponse(id, url, at, user);
    }

}
