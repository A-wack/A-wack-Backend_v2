package org.awack.domain.dailysong.dto;

import jakarta.validation.constraints.Pattern;
import org.awack.domain.dailysong.model.DailySong;
import org.awack.domain.user.model.UserEntity;

public record DailySongApplyRequest(
        @Pattern(regexp = "https?://(?:www\\.)?(?:youtu\\.be/|youtube\\.com/\\S*?[?&]v=)([\\w-]+)", message = "유튜브 링크를 입력해주세요.")
        String url
) {

        public static DailySong toEntity(String url, UserEntity user) {
                return new DailySong(url, user);
        }

}
