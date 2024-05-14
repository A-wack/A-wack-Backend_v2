package org.awack.domain.dailysong_history.dao;

import org.awack.domain.dailysong_history.model.DailySongHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailySongHistoryRepository extends JpaRepository<DailySongHistory, Long> {
    Page<DailySongHistory> findAllByUserId(Long userId, Pageable pageable);
    Page<DailySongHistory> findAllByOrderByCreatedAtDesc(Pageable pageable);

}
