package org.devops.iweb.auth.repository;

import org.devops.core.model.core.AbstractModelRepository;
import org.devops.iweb.auth.model.AuthCompany;
import org.springframework.stereotype.Repository;

@Repository
public class AuthCompanyRepository extends AbstractModelRepository<AuthCompany, Long>{


    public AuthCompany getByAccount(String account) {
		return where(AuthCompany.ACCOUNT,account).get();
    }
}
