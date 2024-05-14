package org.awack.domain.current_dailysong.service;

import lombok.RequiredArgsConstructor;
import org.awack.domain.current_dailysong.port.DeleteCurrentDailySongPort;
import org.awack.domain.current_dailysong.usecase.CancelAdoptionDailySongUseCase;
import org.awack.domain.dailysong_history.port.DeleteDailySongHistoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Service
@Transactional
@RequiredArgsConstructor
public class CancelAdoptionDailySongService implements CancelAdoptionDailySongUseCase {
    private final DeleteCurrentDailySongPort deleteCurrentDailySongPort;
    private final DeleteDailySongHistoryPort deleteDailySongHistoryPort;

    @Override
    public void cancel(Long id) {
//        ForkJoinPool pool = new ForkJoinPool(2);
//
//        pool.execute(() -> deleteCurrentDailySongPort.deleteById(id));
//        pool.execute(() -> deleteDailySongHistoryPort.deleteById(id));
//
//        pool.close();
        CompletableFuture.runAsync(() -> deleteCurrentDailySongPort.deleteById(id))
                .thenRun(() -> deleteDailySongHistoryPort.deleteById(id))
                .join();
    }

}
