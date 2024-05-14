package org.awack.domain.current_dailysong.api;

import lombok.RequiredArgsConstructor;
import org.awack.domain.current_dailysong.usecase.AdoptDailySongUseCase;
import org.awack.domain.current_dailysong.usecase.CancelAdoptionDailySongUseCase;
import org.awack.domain.current_dailysong.usecase.FindCurrentDailySongUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/songs/current")
public class CurrentDailySongController {
    private final AdoptDailySongUseCase adoptDailySongUseCase;
    private final FindCurrentDailySongUseCase findCurrentDailySongUseCase;
    private final CancelAdoptionDailySongUseCase cancelAdoptionDailySongUseCase;

    @GetMapping
    public List<?> getCurrent() {
        return findCurrentDailySongUseCase.findAll();
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable Long id) {
        adoptDailySongUseCase.adopt(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        cancelAdoptionDailySongUseCase.cancel(id);
    }

}
