package org.awack.domain.current_dailysong.port;

import org.awack.domain.current_dailysong.model.CurrentDailySong;

import java.util.List;

public interface FindCurrentDailySongPort {
    List<CurrentDailySong> findAll();

}
