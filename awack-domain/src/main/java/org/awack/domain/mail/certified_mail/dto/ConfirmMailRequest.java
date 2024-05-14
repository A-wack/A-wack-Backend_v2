package org.awack.domain.mail.certified_mail.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ConfirmMailRequest(
        @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(dsm\\.hs\\.kr)$")
        String email,
        @Size(min = 6, max = 6)
        String code
) {
}
