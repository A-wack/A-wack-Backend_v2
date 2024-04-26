package org.awack.domain.mail.authcode.dao;

import org.awack.domain.mail.authcode.model.AuthCode;
import org.springframework.data.repository.CrudRepository;

public interface AuthCodeRepository extends CrudRepository<AuthCode, String> {
}
