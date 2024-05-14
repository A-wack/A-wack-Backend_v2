package org.awack.domain.mail.certified_mail.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.awack.domain.mail.certified_mail.dto.ConfirmMailRequest;
import org.awack.domain.mail.usecase.ConfirmMailUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mail/confirm")
public class CertifiedMailController {
    private final ConfirmMailUseCase confirmMailUseCase;

    @PostMapping
    public void confirm(@Valid @RequestBody ConfirmMailRequest request) {
        confirmMailUseCase.confirm(request);
    }

}
