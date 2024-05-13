package org.awack.utils;

import java.util.List;

public record PagingResponse<T>(
        List<T> data,
        Long totalElements,
        Integer totalPages
) {

    public static <T> PagingResponse<T> of(List<T> data, Long totalElements, Integer totalPages) {
        return new PagingResponse<>(data, totalElements, totalPages);
    }
}
