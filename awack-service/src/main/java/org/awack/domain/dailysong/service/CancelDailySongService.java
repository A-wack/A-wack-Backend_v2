package org.awack.domain.dailysong.service;

import lombok.RequiredArgsConstructor;
import org.awack.custom.NotAllowedUserException;
import org.awack.custom.DailySongNotFoundException;
import org.awack.custom.UserNotFoundException;
import org.awack.domain.dailysong.model.DailySong;
import org.awack.domain.dailysong.port.DeleteDailySongPort;
import org.awack.domain.dailysong.port.FindDailySongPort;
import org.awack.domain.dailysong.usecase.CancelDailySongUseCase;
import org.awack.domain.user.component.CurrentUser;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CancelDailySongService implements CancelDailySongUseCase {
    private final DeleteDailySongPort deleteDailySongPort;
    private final FindDailySongPort findDailySongPort;
    private final CurrentUser currentUser;

    @Override
    public void cancel(Long id) {
        var songRequest = findDailySongPort.findById(id)
                .orElseThrow(DailySongNotFoundException::new);

        validateCurrentUser(songRequest);
        deleteDailySongPort.deleteById(id);
    }

    private void validateCurrentUser(DailySong songRequest) {
        if (songRequest.getUser() != currentUser.get()) {
            throw new NotAllowedUserException();
        }
    }

}
