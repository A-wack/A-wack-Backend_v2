package org.awack.domain.dailysong_history.service;

import lombok.RequiredArgsConstructor;
import org.awack.domain.dailysong_history.dto.FindDailySongHistoryAdminResponse;
import org.awack.domain.dailysong_history.dto.FindDailySongHistoryStudentResponse;
import org.awack.domain.dailysong_history.port.FindDailySongHistoryPort;
import org.awack.domain.dailysong_history.usecase.FindDailySongHistoryUseCase;
import org.awack.utils.PagingInfo;
import org.awack.domain.user.component.CurrentUser;
import org.awack.domain.user.model.UserRole;
import org.awack.utils.PagingResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindDailySongHistoryService implements FindDailySongHistoryUseCase {
    private final FindDailySongHistoryPort findDailySongHistoryPort;
    private final CurrentUser currentUser;

    @Override
    public PagingResponse<Object> findAll(PagingInfo request) {
        var user = currentUser.get();
        var histories = findDailySongHistoryPort.findAll(request.pageable());

        List<?> responses;

        if (user.getRole().equals(UserRole.ADMIN)) {
            responses = histories
                    .stream()
                    .map(FindDailySongHistoryAdminResponse::of)
                    .toList();
        } else {
            responses = histories
                    .stream()
                    .map(FindDailySongHistoryStudentResponse::of)
                    .toList();
        }

        return PagingResponse.of((List<Object>) responses, histories.getTotalElements(), histories.getTotalPages());
    }

    @Override
    public PagingResponse<FindDailySongHistoryStudentResponse> findAllByCurrentUser(PagingInfo request) {
        var responses = findDailySongHistoryPort.findAllByUser(
                currentUser.get().getId(),
                request.pageable()
        );

        var result = responses.stream()
                .map(FindDailySongHistoryStudentResponse::of)
                .toList();

        return PagingResponse.of(result, responses.getTotalElements(), responses.getTotalPages());
    }

}
