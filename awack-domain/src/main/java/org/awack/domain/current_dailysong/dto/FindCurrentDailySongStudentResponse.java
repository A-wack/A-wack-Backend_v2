package org.awack.domain.current_dailysong.dto;

import java.time.LocalDateTime;

public record FindCurrentDailySongStudentResponse(
        Long id,
        String url,
        LocalDateTime at
) {

    public static FindCurrentDailySongStudentResponse of(Long id, String url, LocalDateTime at) {
        return new FindCurrentDailySongStudentResponse(id, url, at);
    }

}
