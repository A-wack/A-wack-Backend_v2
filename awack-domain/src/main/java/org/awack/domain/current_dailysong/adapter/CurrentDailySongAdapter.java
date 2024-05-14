package org.awack.domain.current_dailysong.adapter;

import lombok.RequiredArgsConstructor;
import org.awack.domain.current_dailysong.dao.CurrentDailySongRepository;
import org.awack.domain.current_dailysong.model.CurrentDailySong;
import org.awack.domain.current_dailysong.port.DeleteCurrentDailySongPort;
import org.awack.domain.current_dailysong.port.FindCurrentDailySongPort;
import org.awack.domain.current_dailysong.port.SaveCurrentDailySongPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CurrentDailySongAdapter implements FindCurrentDailySongPort, SaveCurrentDailySongPort, DeleteCurrentDailySongPort {
    private final CurrentDailySongRepository currentDailySongRepository;

    @Override
    public void deleteById(Long id) {
        currentDailySongRepository.deleteById(id);
    }

    @Override
    public List<CurrentDailySong> findAll() {
        return currentDailySongRepository.findAll()
                .stream()
                .limit(3)
                .toList();
    }

    @Override
    public CurrentDailySong save(CurrentDailySong currentDailySong) {
        return currentDailySongRepository.save(currentDailySong);
    }

}
