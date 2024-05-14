package org.awack.domain.dailysong_history.dto;

import org.awack.domain.dailysong_history.model.DailySongHistory;
import org.awack.domain.user.dto.UserDetail;

import java.time.LocalDateTime;

public record FindDailySongHistoryAdminResponse(
    Long id,
    String url,
    LocalDateTime at,
    UserDetail student
) {

    public static FindDailySongHistoryAdminResponse of(DailySongHistory entity) {
        var user = entity.getUser();

        return new FindDailySongHistoryAdminResponse(
                entity.getId(),
                entity.getUrl(),
                entity.getCreatedAt(),
                UserDetail.of(
                        user.getId(),
                        user.getName()
                )
        );
    }

}
