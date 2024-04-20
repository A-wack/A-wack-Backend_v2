package org.awack.domain.user.dto;

public record UserDetail(
        Long id,
        String name
) {

    public static UserDetail of(Long id, String name) {
        return new UserDetail(id, name);
    }

}
