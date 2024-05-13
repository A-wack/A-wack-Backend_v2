package org.awack.custom;

import org.awack.error.ErrorCode;
import org.awack.error.exception.CustomException;

public class UnverifiedEmailException extends CustomException {
    public UnverifiedEmailException() {
        super(ErrorCode.UNVERIFIED_EMAIL);
    }
}
