package org.awack.custom;

import org.awack.error.ErrorCode;
import org.awack.error.exception.CustomException;

public class SongRequestNotFoundException extends CustomException {
    public static final CustomException EXCEPTION
            = new SongRequestNotFoundException();

    private SongRequestNotFoundException() {
        super(ErrorCode.SONG_NOT_FOUND_BY_ID);
    }

}
