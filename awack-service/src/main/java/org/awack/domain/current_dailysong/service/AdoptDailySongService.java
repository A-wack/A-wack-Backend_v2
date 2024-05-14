package org.awack.domain.current_dailysong.service;

import lombok.RequiredArgsConstructor;
import org.awack.custom.DailySongNotFoundException;
import org.awack.domain.current_dailysong.model.CurrentDailySong;
import org.awack.domain.current_dailysong.port.SaveCurrentDailySongPort;
import org.awack.domain.current_dailysong.usecase.AdoptDailySongUseCase;
import org.awack.domain.dailysong_history.model.DailySongHistory;
import org.awack.domain.dailysong_history.port.SaveDailySongHistoryPort;
import org.awack.domain.dailysong.port.FindDailySongPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdoptDailySongService implements AdoptDailySongUseCase {
    private final SaveCurrentDailySongPort saveCurrentDailySongPort;
    private final SaveDailySongHistoryPort saveDailySongHistoryPort;
    private final FindDailySongPort findDailySongPort;

    @Override
    public void adopt(final Long requestId) {
        var dailySong = findDailySongPort.findById(requestId)
                .orElseThrow(DailySongNotFoundException::new);

        var dailySongHistory = saveDailySongHistoryPort.save(
                DailySongHistory.of(
                        dailySong.getId(),
                        dailySong.getUrl(),
                        dailySong.getUser()
                )
        );

        saveCurrentDailySongPort.save(
                CurrentDailySong.of(
                        dailySongHistory.getId(),
                        dailySong.getUrl()
                )
        );

        dailySong.adopt();
    }

}
