package org.awack.domain.dailysong.service;

import lombok.RequiredArgsConstructor;
import org.awack.utils.PagingInfo;
import org.awack.domain.dailysong.dto.DailySongAdminResponse;
import org.awack.domain.dailysong.dto.DailySongStudentResponse;
import org.awack.domain.dailysong.port.FindDailySongPort;
import org.awack.domain.dailysong.usecase.FindDailySongUseCase;
import org.awack.domain.user.component.CurrentUser;
import org.awack.utils.PagingResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindDailySongService implements FindDailySongUseCase {
    private final FindDailySongPort findDailySongPort;
    private final CurrentUser currentUser;

    @Override
    public PagingResponse<DailySongStudentResponse> findAllByUser(PagingInfo request) {
        var responses = findDailySongPort.findAllByUserId(
                currentUser.get().getId(),
                request.pageable()
        );

        var result = responses.stream()
                .map(DailySongStudentResponse::of)
                .toList();

        return PagingResponse.of(result, responses.getTotalElements(), responses.getTotalPages());
    }

    @Override
    public PagingResponse<DailySongAdminResponse> findAll(PagingInfo request) {
        var responses = findDailySongPort.findAll(request.pageable());

        var result = responses.stream()
                .map(DailySongAdminResponse::of)
                .toList();

        return PagingResponse.of(result, responses.getTotalElements(), responses.getTotalPages());
    }

}
