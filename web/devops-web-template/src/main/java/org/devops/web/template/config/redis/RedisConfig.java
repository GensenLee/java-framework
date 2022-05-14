package org.devops.web.template.config.redis;

import lombok.extern.slf4j.Slf4j;
import org.devops.core.utils.modules.redis.configuration.EnableRedis;
import org.devops.core.utils.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableAutoConfiguration
@EnableRedis
@Slf4j
@EnableConfigurationProperties({RedisLettucePoolProperties.class, RedisProperties.class})
public class RedisConfig {
	
	@Autowired
	private RedisProperties redisProperties;
	
	@Autowired
	private RedisLettucePoolProperties redisLettucePoolProperties;
	
	@Bean
	public JedisPool jedisPool() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(redisLettucePoolProperties.getMaxIdle());
		config.setMaxTotal(redisLettucePoolProperties.getMaxTotal());
		config.setBlockWhenExhausted(redisProperties.isBlockWhenExhausted());
		config.setTestOnBorrow(redisProperties.isTestOnBorrow());
		config.setMaxWaitMillis(redisLettucePoolProperties.getMaxWait());
		return new JedisPool(config,redisProperties.getHost(),redisProperties.getPort(),redisProperties.getTimeout(),redisProperties.getPassword());
	}

	@Bean
	@Conditional(JedisClusterCondition.class)
	public JedisCluster jedisCluster() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(redisLettucePoolProperties.getMaxIdle());
		config.setMaxTotal(redisLettucePoolProperties.getMaxTotal());
		config.setBlockWhenExhausted(redisProperties.isBlockWhenExhausted());
		config.setTestOnBorrow(redisProperties.isTestOnBorrow());
		config.setMaxWaitMillis(redisLettucePoolProperties.getMaxWait());
		Set<HostAndPort> jedisNodes = new HashSet<HostAndPort>();
		if(redisProperties.getNodes() != null && redisProperties.getNodes().length > 0) {
			for(String node : redisProperties.getNodes()) {
				jedisNodes.add(new HostAndPort(node.split(":")[0], Integer.parseInt(node.split(":")[1])));
			}
		} else {
			return null;
		}
		return new JedisCluster(jedisNodes, redisProperties.getTimeout(),redisProperties.getTimeout(),redisLettucePoolProperties.getMaxTotal(),redisProperties.getPassword(),config);
	}

	public static class JedisClusterCondition implements Condition {

		@Override
		public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
			String nodes = context.getEnvironment().getProperty("spring.redis.nodes", "");
			if (StringUtil.isEmpty(nodes)) {
				nodes = context.getEnvironment().getProperty("spring.redis.nodes[0]", "");
			}
			return StringUtil.isNotEmpty(nodes);
		}
	}
}
