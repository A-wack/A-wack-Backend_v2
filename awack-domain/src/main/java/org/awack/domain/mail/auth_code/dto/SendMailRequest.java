package org.awack.domain.mail.auth_code.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SendMailRequest(
        @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(dsm\\.hs\\.kr)$")
        String email,
        @Size(min = 8, max = 8)
        String type
) {
}
