package org.awack.domain.dailysong.dto;

import org.awack.domain.dailysong.model.DailySong;
import org.awack.domain.dailysong.model.DailySongStatus;
import org.awack.domain.user.dto.UserDetail;

public record DailySongAdminResponse(
    Long id,
    String url,
    DailySongStatus status,
    UserDetail user
) {

    public static DailySongAdminResponse of(DailySong entity) {
        var user = entity.getUser();

        return new DailySongAdminResponse(
                entity.getId(),
                entity.getUrl(),
                entity.getStatus(),
                UserDetail.of(
                        user.getId(),
                        user.getName()
                )
        );
    }

}
