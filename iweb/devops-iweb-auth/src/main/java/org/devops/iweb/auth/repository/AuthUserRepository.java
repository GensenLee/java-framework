package org.devops.iweb.auth.repository;

import org.devops.core.model.core.AbstractModelRepository;
import org.devops.iweb.auth.model.AuthUser;
import org.springframework.stereotype.Repository;

@Repository
public class AuthUserRepository extends AbstractModelRepository<AuthUser, Long>{

}
