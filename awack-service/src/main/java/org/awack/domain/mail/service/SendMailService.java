package org.awack.domain.mail.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.awack.config.mail.MailProperties;
import org.awack.custom.FailedSendMailException;
import org.awack.custom.NotAllowedUserException;
import org.awack.domain.mail.auth_code.dto.SendMailRequest;
import org.awack.domain.mail.auth_code.model.AuthCode;
import org.awack.domain.mail.auth_code.port.SaveAuthCodePort;
import org.awack.domain.mail.usecase.SendMailUseCase;
import org.awack.domain.user.port.FindUserPort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Base64;

@Service
@Transactional
@RequiredArgsConstructor
public class SendMailService implements SendMailUseCase {
    private final SaveAuthCodePort saveAuthCodePort;
    private final FindUserPort findUserPort;

    private final JavaMailSender javaMailSender;
    private final MailProperties mailProperties;

    @Async
    @Override
    public void send(SendMailRequest request) throws UnsupportedEncodingException {
        validate(request);

        try {
            javaMailSender.send(
                setMimeMessage(
                        request.email()
                )
            );
        } catch (MessagingException e) {
            throw new FailedSendMailException();
        }
    }

    private void validate(SendMailRequest request) {
        if (request.type().equals(MailSendType.REGISTER.getValue())) {
            if (findUserPort.exists(request.email())) {
                throw new NotAllowedUserException();
            }
        } else if(request.type().equals(MailSendType.PASSWORD_CHANGE.getValue()) &&
                (!findUserPort.exists(request.email()))) {
                throw new NotAllowedUserException();
        }
    }

    private MimeMessage setMimeMessage(String to) throws MessagingException, UnsupportedEncodingException {
        var code = getRandomCode();

        var message = javaMailSender.createMimeMessage();

        var helper = new MimeMessageHelper(
                message,
                true,
                "UTF-8"
        );

        helper.setTo(to);
        helper.setSubject("DSM 메일 인증");

        var htmlBody = setTemplate(code);

        helper.setText(
                htmlBody,
                true
        );
        helper.setFrom(
                new InternetAddress(
                        mailProperties.email(),
                "DSM-mail-verify"
                )
        );

        saveAuthCode(
                AuthCode.of(
                        to,
                        code
                )
        );

        return message;
    }

    private void saveAuthCode(AuthCode authCode) {
        saveAuthCodePort.save(
                authCode
        );
    }

    private String setTemplate(String code) {
        return "<div style='margin: 10px; background-color: #f5f5f5; padding: 20px; border-radius: 10px;'>"
                + "<p style='font-size: 16px; color: #333;'><b><span style='color: #007bff;'>D</span><span style='color: #ffcc00;'>S</span><span style='color: #ff0000;'>M</span></b> 이메일 인증 코드 :</p>"
                + "<p style='font-size: 24px; font-weight: bold; color: #007bff; letter-spacing: 3px;'>" + code + "</p>"
                + "<p style='font-size: 14;font-style: italic; color: #999;'>인증 코드는 3시간 동안 유효합니다.</p>"
                + "</div>";
    }

    private String getRandomCode() {
        var secureRandom = new SecureRandom();
        var seed = new byte[32];
        secureRandom.nextBytes(seed);
        secureRandom.setSeed(seed);
        var randomBytes = new byte[4];
        secureRandom.nextBytes(randomBytes);

        return Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(randomBytes);
    }

}

@Getter
@AllArgsConstructor
enum MailSendType {
    PASSWORD_CHANGE("pwChange"),
    REGISTER("register");

    private final String value;
}
