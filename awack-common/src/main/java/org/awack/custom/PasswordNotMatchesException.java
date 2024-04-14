package org.awack.custom;

import org.awack.error.ErrorCode;
import org.awack.error.exception.CustomException;

public class PasswordNotMatchesException extends CustomException {
    public static final CustomException EXCEPTION
            = new PasswordNotMatchesException();

    private PasswordNotMatchesException() {
        super(ErrorCode.PASSWORD_NOT_MATCHES);
    }

}
