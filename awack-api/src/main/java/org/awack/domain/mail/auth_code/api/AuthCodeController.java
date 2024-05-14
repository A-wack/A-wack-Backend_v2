package org.awack.domain.mail.auth_code.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.awack.domain.mail.auth_code.dto.SendMailRequest;
import org.awack.domain.mail.usecase.SendMailUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mail/send")
public class AuthCodeController {
    private final SendMailUseCase sendMailUseCase;

    @PostMapping
    public void send(@Valid @RequestBody SendMailRequest request) throws UnsupportedEncodingException {
        sendMailUseCase.send(request);
    }

}
