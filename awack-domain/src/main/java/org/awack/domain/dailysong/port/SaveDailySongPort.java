package org.awack.domain.dailysong.port;

import org.awack.domain.dailysong.model.DailySong;

public interface SaveDailySongPort {
    DailySong save(DailySong dailySong);

}
