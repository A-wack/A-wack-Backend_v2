package org.awack.domain.dailysong_history.dto;

import org.awack.domain.dailysong_history.model.DailySongHistory;

import java.time.LocalDateTime;

public record FindDailySongHistoryStudentResponse(
    Long id,
    String url,
    LocalDateTime at
) {

    public static FindDailySongHistoryStudentResponse of(DailySongHistory entity) {
        return new FindDailySongHistoryStudentResponse(
                entity.getId(),
                entity.getUrl(),
                entity.getCreatedAt()
        );
    }

}
