package org.awack.domain.dailysong_history.usecase;

import org.awack.domain.dailysong_history.dto.FindDailySongHistoryStudentResponse;
import org.awack.utils.PagingInfo;
import org.awack.utils.PagingResponse;

public interface FindDailySongHistoryUseCase {
    PagingResponse<Object> findAll(PagingInfo request);
    PagingResponse<FindDailySongHistoryStudentResponse> findAllByCurrentUser(PagingInfo request);

}
