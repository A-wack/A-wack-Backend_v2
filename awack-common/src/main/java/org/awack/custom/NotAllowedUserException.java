package org.awack.custom;

import org.awack.error.ErrorCode;
import org.awack.error.exception.CustomException;

public class NotAllowedUserException extends CustomException {
    public NotAllowedUserException() {
        super(ErrorCode.NOT_ALLOWED_USER);
    }
}
