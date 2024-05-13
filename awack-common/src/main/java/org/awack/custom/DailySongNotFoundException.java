package org.awack.custom;

import org.awack.error.ErrorCode;
import org.awack.error.exception.CustomException;

public class DailySongNotFoundException extends CustomException {
    public DailySongNotFoundException() {
        super(ErrorCode.SONG_NOT_FOUND_BY_ID);
    }
}
