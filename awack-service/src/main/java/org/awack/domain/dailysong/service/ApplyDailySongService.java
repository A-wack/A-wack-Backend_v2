package org.awack.domain.dailysong.service;

import lombok.RequiredArgsConstructor;
import org.awack.domain.dailysong.dto.DailySongApplyRequest;
import org.awack.domain.dailysong.port.SaveDailySongPort;
import org.awack.domain.dailysong.usecase.ApplyDailySongUseCase;
import org.awack.domain.user.component.CurrentUser;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplyDailySongService implements ApplyDailySongUseCase {
    private final SaveDailySongPort saveDailySongPort;
    private final CurrentUser currentUser;

    @Override
    public void apply(DailySongApplyRequest request) {
        saveDailySongPort.save(
                DailySongApplyRequest.toEntity(
                        request.url(),
                        currentUser.get()
                )
        );
    }

}
