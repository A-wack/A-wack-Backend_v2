package org.awack.custom;

import org.awack.error.ErrorCode;
import org.awack.error.exception.CustomException;

public class JwtExpiredException extends CustomException {
    public static final CustomException EXCEPTION
            = new JwtExpiredException();

    private JwtExpiredException() {
        super(ErrorCode.JWT_EXPIRED);
    }

}
