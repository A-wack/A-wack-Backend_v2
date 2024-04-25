package org.awack.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 400 Bad Request
    // 401 Unauthorized
    PASSWORD_NOT_MATCHES(401, "비밀번호가 일치하지 않습니다."),
    AUTH_CODE_NOT_MATCHES(401, "인증 번호가 일치하지 않습니다."),
    INVALID_AUTH_CODE(401, "유효하지 않는 인증 코드입니다."),
    JWT_EXPIRED(401, "토큰이 바보 멍청이임"),
    // 402 Payment Required
    // 403 Forbidden
    NOT_ALLOWED_USER(403, "권한이 없는 사용자입니다"),
    UNVERIFIED_EMAIL(403, "해당 이메일은 인증이 되지 않았습니다."),
    // 404 Not Found
    USER_NOT_FOUND(404, "유저를 찾지 못 했습니다."),
    SONG_NOT_FOUND_BY_ID(404, "신청 정보를 찾지 못 했습니다."),
    // 405 Method Not Allowed
    // 406 Not Acceptable
    // 407 Proxy Authentication Required
    // 408 Request Timeout
    // 409 Conflict
    USER_ALREADY_EXISTS_BY_EMAIL(409, "해당 이메일로 가입된 유저가 이미 존재합니다."),
    // 410 Gone
    // 411 Length Required
    // 412 Precondition Failed
    // 413 Payload Too Large
    // 414 URI Too Long
    // 415 Unsupported Media Type
    // 416 Range Not Satisfiable
    // 417 Expectation Failed
    // 418 I'm a teapot
    // 419 Authentication Timeout
    // 420 Method Failure
    // 421 Misdirected Request
    // 422 Unprocessable Entity
    // 423 Locked
    // 424 Failed Dependency
    // 425 Too Early
    // 426 Upgrade Required
    // 428 Precondition Required
    // 429 Too Many Requests
    // 431 Request Header Fields Too Large
    // 451 Unavailable For Legal Reasons
    // 500 Internal Server Error
    FAILED_SEND_MAIL(500, "메일 전송 실패");

    private final int status;
    private final String message;
}
