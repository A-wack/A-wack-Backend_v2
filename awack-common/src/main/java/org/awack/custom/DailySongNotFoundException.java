package org.awack.custom;

import org.awack.error.ErrorCode;
import org.awack.error.exception.CustomException;

public class DailySongNotFoundException extends CustomException {
    public static final CustomException EXCEPTION
            = new DailySongNotFoundException();

    private DailySongNotFoundException() {
        super(ErrorCode.SONG_NOT_FOUND_BY_ID);
    }

}
