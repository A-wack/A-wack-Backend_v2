package org.awack.domain.dailysong_history.api;

import lombok.RequiredArgsConstructor;
import org.awack.domain.dailysong_history.dto.FindDailySongHistoryStudentResponse;
import org.awack.domain.dailysong_history.usecase.FindDailySongHistoryUseCase;
import org.awack.utils.PagingInfo;
import org.awack.utils.PagingResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/songs/histories")
public class DailySongHistoryController {
    private final FindDailySongHistoryUseCase findDailySongHistoryUseCase;

    @GetMapping
    public PagingResponse<Object> findAll(@RequestParam Integer page, @RequestParam Integer size) {
        return findDailySongHistoryUseCase.findAll(PagingInfo.of(page, size));
    }

    @GetMapping("/me")
    public PagingResponse<FindDailySongHistoryStudentResponse> findAllByUser(@RequestParam Integer page, @RequestParam Integer size) {
        return findDailySongHistoryUseCase.findAllByCurrentUser(PagingInfo.of(page, size));
    }

}
