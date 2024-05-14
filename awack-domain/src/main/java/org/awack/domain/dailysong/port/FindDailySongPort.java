package org.awack.domain.dailysong.port;

import org.awack.domain.dailysong.model.DailySong;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface FindDailySongPort {
    Optional<DailySong> findById(Long id);
    Page<DailySong> findAllByUserId(Long userId, Pageable pageable);
    Page<DailySong> findAll(Pageable pageable);

}
