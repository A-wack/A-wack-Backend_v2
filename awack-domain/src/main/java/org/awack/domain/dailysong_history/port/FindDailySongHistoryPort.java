package org.awack.domain.dailysong_history.port;

import org.awack.domain.dailysong_history.model.DailySongHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface FindDailySongHistoryPort {
    Page<DailySongHistory> findAll(Pageable pageable);
    Page<DailySongHistory> findAllByUser(Long userId, Pageable pageable);
    Optional<DailySongHistory> findById(Long id);

}
