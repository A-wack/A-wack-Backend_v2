package org.awack.custom;

import org.awack.error.ErrorCode;
import org.awack.error.exception.CustomException;

public class NotAllowedUserException extends CustomException {
    public static final CustomException EXCEPTION
            = new NotAllowedUserException();

    private NotAllowedUserException() {
        super(ErrorCode.NOT_ALLOWED_USER);
    }

}
