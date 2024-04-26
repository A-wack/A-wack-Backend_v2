package org.awack.util;

public record PagingResponse(
        Object data,
        Long totalElements,
        Integer totalPages
) {

    public static PagingResponse of(Object data, Long totalElements, Integer totalPages) {
        return new PagingResponse(data, totalElements, totalPages);
    }

}
