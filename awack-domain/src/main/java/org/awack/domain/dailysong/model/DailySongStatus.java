package org.awack.domain.dailysong.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DailySongStatus {
    PENDING("신청 중"),
    ADOPTED("채택"),
    CANCELED("거절");

    private final String description;
}
