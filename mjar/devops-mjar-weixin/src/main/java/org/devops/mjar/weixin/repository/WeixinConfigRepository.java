package org.devops.mjar.weixin.repository;

import org.devops.core.model.core.AbstractModelRepository;
import org.springframework.stereotype.Repository;

import org.devops.mjar.weixin.model.WeixinConfig;

@Repository
public class WeixinConfigRepository extends AbstractModelRepository<WeixinConfig, Long> {
}
