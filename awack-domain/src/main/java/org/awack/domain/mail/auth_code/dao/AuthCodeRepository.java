package org.awack.domain.mail.auth_code.dao;

import org.awack.domain.mail.auth_code.model.AuthCode;
import org.springframework.data.repository.CrudRepository;

public interface AuthCodeRepository extends CrudRepository<AuthCode, String> {
}
