package org.awack.custom;

import org.awack.error.ErrorCode;
import org.awack.error.exception.CustomException;

public class JwtExpiredException extends CustomException {
    public JwtExpiredException() {
        super(ErrorCode.JWT_EXPIRED);
    }
}
