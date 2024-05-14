package org.awack.domain.dailysong.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.awack.domain.dailysong.dto.DailySongAdminResponse;
import org.awack.domain.dailysong.dto.DailySongStudentResponse;
import org.awack.domain.dailysong.usecase.RejectDailySongUseCase;
import org.awack.utils.PagingInfo;
import org.awack.domain.dailysong.dto.DailySongApplyRequest;
import org.awack.domain.dailysong.usecase.ApplyDailySongUseCase;
import org.awack.domain.dailysong.usecase.CancelDailySongUseCase;
import org.awack.domain.dailysong.usecase.FindDailySongUseCase;
import org.awack.utils.PagingResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/songs")
public class DailySongController {
    private final ApplyDailySongUseCase applyDailySongUseCase;
    private final CancelDailySongUseCase cancelDailySongUseCase;
    private final FindDailySongUseCase findDailySongUseCase;
    private final RejectDailySongUseCase rejectDailySongUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void apply(@Valid @RequestBody DailySongApplyRequest request) {
        applyDailySongUseCase.apply(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancel(@PathVariable Long id) {
        cancelDailySongUseCase.cancel(id);
    }

    @GetMapping
    public PagingResponse<DailySongStudentResponse> findAllByCurrentUser(@RequestParam Integer page, @RequestParam Integer size) {
        return findDailySongUseCase.findAllByUser(PagingInfo.of(page, size));
    }

    @GetMapping("/admin")
    public PagingResponse<DailySongAdminResponse> findAll(@RequestParam Integer page, @RequestParam Integer size) {
        return findDailySongUseCase.findAll(PagingInfo.of(page, size));
    }

    @PatchMapping("/{id}")
    public void reject(@PathVariable Long id) {
        rejectDailySongUseCase.reject(id);
    }

}
