package org.awack.domain.auth.usecase;

import org.awack.domain.auth.dto.CreateTokenRequest;
import org.awack.domain.auth.dto.CreateTokenResponse;

public interface CreateTokenUseCase {
    CreateTokenResponse create(CreateTokenRequest request);
    CreateTokenResponse reissue(String token);

}
