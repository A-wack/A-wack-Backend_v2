package org.awack.domain.current_dailysong.port;

import org.awack.domain.current_dailysong.model.CurrentDailySong;

public interface SaveCurrentDailySongPort {
    CurrentDailySong save(CurrentDailySong currentDailySong);

}
