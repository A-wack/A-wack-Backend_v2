package org.awack.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public record PagingInfo(
        Integer page,
        Integer size
) {

    public static PagingInfo of(Integer page, Integer size) {
        return new PagingInfo(page, size);
    }

    public Pageable pageable() {
        return PageRequest.of(
                page,
                size
        );
    }
}
