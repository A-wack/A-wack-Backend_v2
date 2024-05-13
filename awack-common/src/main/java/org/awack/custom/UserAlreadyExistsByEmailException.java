package org.awack.custom;

import org.awack.error.ErrorCode;
import org.awack.error.exception.CustomException;

public class UserAlreadyExistsByEmailException extends CustomException {
    public UserAlreadyExistsByEmailException() {
        super(ErrorCode.USER_ALREADY_EXISTS_BY_EMAIL);
    }
}
