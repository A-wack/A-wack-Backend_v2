package org.awack.domain.dailysong_history.adapter;

import lombok.RequiredArgsConstructor;
import org.awack.domain.dailysong_history.dao.DailySongHistoryRepository;
import org.awack.domain.dailysong_history.model.DailySongHistory;
import org.awack.domain.dailysong_history.port.DeleteDailySongHistoryPort;
import org.awack.domain.dailysong_history.port.FindDailySongHistoryPort;
import org.awack.domain.dailysong_history.port.SaveDailySongHistoryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DailySongHistoryAdapter implements FindDailySongHistoryPort, SaveDailySongHistoryPort, DeleteDailySongHistoryPort {
    private final DailySongHistoryRepository dailySongHistoryRepository;

    @Override
    public Page<DailySongHistory> findAll(Pageable pageable) {
        return dailySongHistoryRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    @Override
    public Page<DailySongHistory> findAllByUser(Long userId, Pageable pageable) {
        return dailySongHistoryRepository.findAllByUserId(userId, pageable);
    }

    @Override
    public Optional<DailySongHistory> findById(Long id) {
        return dailySongHistoryRepository.findById(id);
    }

    @Override
    public DailySongHistory save(DailySongHistory dailySongHistory) {
        return dailySongHistoryRepository.save(dailySongHistory);
    }

    @Override
    public void deleteById(Long id) {
        dailySongHistoryRepository.deleteById(id);
    }

}
