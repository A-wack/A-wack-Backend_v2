package org.awack.domain.current_dailysong.service;

import lombok.RequiredArgsConstructor;
import org.awack.custom.DailySongNotFoundException;
import org.awack.domain.current_dailysong.model.CurrentDailySong;
import org.awack.domain.current_dailysong.port.FindCurrentDailySongPort;
import org.awack.domain.current_dailysong.usecase.FindCurrentDailySongUseCase;
import org.awack.domain.current_dailysong.dto.FindCurrentDailySongAdminResponse;
import org.awack.domain.current_dailysong.dto.FindCurrentDailySongStudentResponse;
import org.awack.domain.dailysong_history.model.DailySongHistory;
import org.awack.domain.dailysong_history.port.FindDailySongHistoryPort;
import org.awack.domain.user.component.CurrentUser;
import org.awack.domain.user.dto.UserDetail;
import org.awack.domain.user.model.UserEntity;
import org.awack.domain.user.model.UserRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindCurrentDailySongService implements FindCurrentDailySongUseCase {
    private final FindDailySongHistoryPort findDailySongHistoryPort;
    private final FindCurrentDailySongPort findCurrentDailySongPort;
    private final CurrentUser currentUser;

    @Override
    public List<?> findAll() {
        var user = currentUser.get();
        var currentDailySongs = findCurrentDailySongPort.findAll();
        var dailySongHistories = mapToDailySongHistories(currentDailySongs);

        if (user.getRole().equals(UserRole.ADMIN)) {
            return mapToAdminResponse(dailySongHistories);
        } else {
            return mapToStudentResponse(dailySongHistories);
        }
    }

    private List<DailySongHistory> mapToDailySongHistories(List<CurrentDailySong> currentDailySongs) {
        return currentDailySongs.stream()
                .map(dailySong-> findDailySong(dailySong.id()))
                .toList();
    }

    private DailySongHistory findDailySong(Long id) {
        return findDailySongHistoryPort.findById(id)
                .orElseThrow(DailySongNotFoundException::new);
    }

    private List<FindCurrentDailySongAdminResponse> mapToAdminResponse(List<DailySongHistory> dailySongHistories) {
        return dailySongHistories.stream()
                .map(dailySongHistory -> {
                    var user = dailySongHistory.getUser();

                    return FindCurrentDailySongAdminResponse.of(
                        dailySongHistory.getId(),
                        dailySongHistory.getUrl(),
                        dailySongHistory.getCreatedAt(),
                        UserDetail.of(
                                user.getId(),
                                user.getName()
                        ));
                }).toList();
    }

    private List<FindCurrentDailySongStudentResponse> mapToStudentResponse(List<DailySongHistory> dailySongHistories) {
        return dailySongHistories.stream()
                .map(dailySongHistory -> FindCurrentDailySongStudentResponse.of(
                        dailySongHistory.getId(),
                        dailySongHistory.getUrl(),
                        dailySongHistory.getCreatedAt()
                )).toList();
    }

}
