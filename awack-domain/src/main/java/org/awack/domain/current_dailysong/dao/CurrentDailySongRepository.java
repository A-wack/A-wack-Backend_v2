package org.awack.domain.current_dailysong.dao;

import org.awack.domain.current_dailysong.model.CurrentDailySong;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CurrentDailySongRepository extends CrudRepository<CurrentDailySong, Long> {
    @Override
    List<CurrentDailySong> findAll();

}
