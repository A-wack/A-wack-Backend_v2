package org.awack.domain.dailysong.adapter;

import lombok.RequiredArgsConstructor;
import org.awack.domain.dailysong.dao.DailySongRepository;
import org.awack.domain.dailysong.model.DailySong;
import org.awack.domain.dailysong.port.DeleteDailySongPort;
import org.awack.domain.dailysong.port.FindDailySongPort;
import org.awack.domain.dailysong.port.SaveDailySongPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DailySongAdapter implements FindDailySongPort, SaveDailySongPort, DeleteDailySongPort {
    private final DailySongRepository dailySongRepository;

    @Override
    public void deleteById(Long id) {
        dailySongRepository.deleteById(id);
    }

    @Override
    public Optional<DailySong> findById(Long id) {
        return dailySongRepository.findById(id);
    }

    @Override
    public Page<DailySong> findAllByUserId(Long userId, Pageable pageable) {
        return dailySongRepository.findAllByUserIdOrderByCreatedAtDesc(userId, pageable);
    }

    @Override
    public Page<DailySong> findAll(Pageable pageable) {
        return dailySongRepository.findAll(pageable);
    }

    @Override
    public DailySong save(DailySong dailySong) {
        return dailySongRepository.save(dailySong);
    }

}
