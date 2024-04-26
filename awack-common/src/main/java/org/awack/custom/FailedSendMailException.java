package org.awack.custom;

import org.awack.error.ErrorCode;
import org.awack.error.exception.CustomException;

public class FailedSendMailException extends CustomException {
    public static final CustomException EXCEPTION = new FailedSendMailException();

    private FailedSendMailException() {
        super(ErrorCode.FAILED_SEND_MAIL);
    }

}
