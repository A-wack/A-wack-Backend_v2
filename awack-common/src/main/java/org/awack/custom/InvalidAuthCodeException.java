package org.awack.custom;

import org.awack.error.ErrorCode;
import org.awack.error.exception.CustomException;

public class InvalidAuthCodeException extends CustomException {
    public InvalidAuthCodeException() {
        super(ErrorCode.INVALID_AUTH_CODE);
    }
}
