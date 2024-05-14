package org.awack.domain.dailysong.service;

import lombok.RequiredArgsConstructor;
import org.awack.custom.DailySongNotFoundException;
import org.awack.domain.dailysong.model.DailySong;
import org.awack.domain.dailysong.port.FindDailySongPort;
import org.awack.domain.dailysong.usecase.RejectDailySongUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RejectDailySongService implements RejectDailySongUseCase {
    private final FindDailySongPort findDailySongPort;

    @Override
    public void reject(Long id) {
        var dailySong = findDailySongPort.findById(id)
                .orElseThrow(DailySongNotFoundException::new);

        dailySong.cancel();
    }

}
