package org.awack.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.awack.error.ErrorCode;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;

}
