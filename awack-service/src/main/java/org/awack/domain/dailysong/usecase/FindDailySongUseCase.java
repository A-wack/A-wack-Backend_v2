package org.awack.domain.dailysong.usecase;

import org.awack.domain.dailysong.dto.DailySongAdminResponse;
import org.awack.domain.dailysong.dto.DailySongStudentResponse;
import org.awack.utils.PagingInfo;
import org.awack.utils.PagingResponse;

public interface FindDailySongUseCase {
    PagingResponse<DailySongStudentResponse> findAllByUser(PagingInfo request);
    PagingResponse<DailySongAdminResponse> findAll(PagingInfo request);

}
