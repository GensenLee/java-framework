package org.devops.iweb.auth.repository;

import org.devops.core.model.core.AbstractModelRepository;
import org.devops.iweb.auth.model.AuthLoginLog;
import org.springframework.stereotype.Repository;

@Repository
public class AuthLoginLogRepository extends AbstractModelRepository<AuthLoginLog, Long>{

}
