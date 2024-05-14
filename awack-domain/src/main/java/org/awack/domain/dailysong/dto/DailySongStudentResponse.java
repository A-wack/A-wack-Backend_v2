package org.awack.domain.dailysong.dto;

import lombok.Builder;
import org.awack.domain.dailysong.model.DailySong;
import org.awack.domain.dailysong.model.DailySongStatus;

public record DailySongStudentResponse(
        Long id,
        String url,
        DailySongStatus status
) {

    public static DailySongStudentResponse of(DailySong entity) {
        return new DailySongStudentResponse(
                entity.getId(),
                entity.getUrl(),
                entity.getStatus()
        );
    }

}
