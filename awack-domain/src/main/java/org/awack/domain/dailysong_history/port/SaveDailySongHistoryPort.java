package org.awack.domain.dailysong_history.port;

import org.awack.domain.dailysong_history.model.DailySongHistory;

public interface SaveDailySongHistoryPort {
    DailySongHistory save(DailySongHistory dailySongHistory);

}
