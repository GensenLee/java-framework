package org.devops.core.utils.modules.redis.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.devops.core.utils.constant.CommonConstant;
import org.devops.core.utils.exception.CommonRuntimeException;
import org.devops.core.utils.modules.redis.transaction.RedisSimulatedTransactionHandler;
import org.devops.core.utils.modules.redis.util.JedisClusterUtil;

import java.util.Set;

import org.devops.core.utils.util.StringUtil;
import redis.clients.jedis.BitPosParams;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.ListPosition;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.StreamConsumersInfo;
import redis.clients.jedis.StreamEntry;
import redis.clients.jedis.StreamEntryID;
import redis.clients.jedis.StreamGroupInfo;
import redis.clients.jedis.StreamInfo;
import redis.clients.jedis.StreamPendingEntry;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.commands.JedisCommands;
import redis.clients.jedis.params.GeoRadiusParam;
import redis.clients.jedis.params.SetParams;
import redis.clients.jedis.params.ZAddParams;
import redis.clients.jedis.params.ZIncrByParams;

public class RedisClient implements JedisCommands {

	/**
	 * key 前缀
	 */
	private String prefix = "";
	
	private Jedis jedis;
	
	private JedisCluster jedisCluster;
	
	private Map<Integer,Pipeline> mapPipeline = new HashMap<Integer,Pipeline>();
	private List<Jedis> liJedis = new ArrayList<>();
	
	public RedisClient(String prefix, JedisCluster jedisCluster) {
		this.prefix = prefix;
		this.jedisCluster = jedisCluster;
	}

	public RedisClient(String prefix, Jedis jedis) {
		this.prefix = prefix;
		this.jedis = jedis;
	}

	private String prepostKey(String key) {
		try {
			key = StringUtil.isEmpty(this.prefix) ? key : this.prefix + CommonConstant.COLON_MARK + key;
			StackTraceElement[] stack = Thread.currentThread().getStackTrace();
			// AspectRedisDao 子类
			StackTraceElement traceElement = stack[3];
			RedisSimulatedTransactionHandler.addKey(key, traceElement.getClassName() + CommonConstant.POUND_MARK + traceElement.getMethodName());
		}catch (Exception ignored){}
		return key;
	}

	public ScanResult<String> scan(String cursor, ScanParams params){
		if(jedisCluster != null) {
			return jedisCluster.scan(cursor, params);
		}
		return jedis.scan(cursor, params);
	}

	@Override
	public String set(String key, String value) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.set(key, value);
		}
		return jedis.set(key, value);
	}

	@Override
	public String set(String key, String value, SetParams params) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.set(key, value, params);
		}
		return jedis.set(key, value, params);
	}

	@Override
	public String get(String key) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.get(key);
		}
		return jedis.get(key);
	}

	@Override
	public Boolean exists(String key) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.exists(key);
		}
		return jedis.exists(key);
	}

	@Override
	public Long persist(String key) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.persist(key);
		}
		return jedis.persist(key);
	}

	@Override
	public String type(String key) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.type(key);
		}
		return jedis.type(key);
	}

	@Override
	public byte[] dump(String key) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.dump(key);
		}
		return jedis.dump(key);
	}

	@Override
	public String restore(String key, int ttl, byte[] serializedValue) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.restore(key, ttl, serializedValue);
		}
		return jedis.restore(key, ttl, serializedValue);
	}

	@Override
	public String restoreReplace(String key, int ttl, byte[] serializedValue) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.restore(key, ttl, serializedValue);
		}
		return jedis.restoreReplace(key, ttl, serializedValue);
	}

	@Override
	public Long expire(String key, int seconds) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.expire(key, seconds);
		}
		return jedis.expire(key, seconds);
	}

	@Override
	public Long pexpire(String key, long milliseconds) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.pexpire(key, milliseconds);
		}
		return jedis.pexpire(key, milliseconds);
	}

	@Override
	public Long expireAt(String key, long unixTime) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.expireAt(key, unixTime);
		}
		return jedis.expireAt(key, unixTime);
	}

	@Override
	public Long pexpireAt(String key, long millisecondsTimestamp) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.pexpireAt(key, millisecondsTimestamp);
		}
		return jedis.pexpireAt(key, millisecondsTimestamp);
	}

	@Override
	public Long ttl(String key) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.ttl(key);
		}
		return jedis.ttl(key);
	}

	@Override
	public Long pttl(String key) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.pttl(key);
		}
		return jedis.pttl(key);
	}

	@Override
	public Long touch(String key) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.touch(key);
		}
		return jedis.touch(key);
	}

	@Override
	public Boolean setbit(String key, long offset, boolean value) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.setbit(key, offset, value);
		}
		return jedis.setbit(key, offset, value);
	}

	@Override
	public Boolean setbit(String key, long offset, String value) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.setbit(key, offset, value);
		}
		return jedis.setbit(key, offset, value);
	}

	@Override
	public Boolean getbit(String key, long offset) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.getbit(key, offset);
		}
		return jedis.getbit(key, offset);
	}

	@Override
	public Long setrange(String key, long offset, String value) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.setrange(key, offset, value);
		}
		return jedis.setrange(key, offset, value);
	}

	@Override
	public String getrange(String key, long startOffset, long endOffset) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.getrange(key, startOffset, endOffset);
		}
		return jedis.getrange(key, startOffset, endOffset);
	}

	@Override
	public String getSet(String key, String value) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.getSet(key, value);
		}
		return jedis.getSet(key, value);
	}

	@Override
	public Long setnx(String key, String value) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.setnx(key, value);
		}
		return jedis.setnx(key, value);
	}

	@Override
	public String setex(String key, int seconds, String value) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.setex(key, seconds, value);
		}
		return jedis.setex(key, seconds, value);
	}

	@Override
	public String psetex(String key, long milliseconds, String value) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.psetex(key, milliseconds, value);
		}
		return jedis.psetex(key, milliseconds, value);
	}

	@Override
	public Long decrBy(String key, long decrement) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.decrBy(key, decrement);
		}
		return jedis.decrBy(key, decrement);
	}

	@Override
	public Long decr(String key) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.decr(key);
		}
		return jedis.decr(key);
	}

	@Override
	public Long incrBy(String key, long increment) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.incrBy(key, increment);
		}
		return jedis.incrBy(key, increment);
	}

	@Override
	public Double incrByFloat(String key, double increment) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.incrByFloat(key, increment);
		}
		return jedis.incrByFloat(key, increment);
	}

	@Override
	public Long incr(String key) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.incr(key);
		}
		return jedis.incr(key);
	}

	@Override
	public Long append(String key, String value) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.append(key, value);
		}
		return jedis.append(key, value);
	}

	@Override
	public String substr(String key, int start, int end) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.substr(key, start, end);
		}
		return jedis.substr(key, start, end);
	}

	@Override
	public Long hset(String key, String field, String value) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.hset(key, field, value);
		}
		return jedis.hset(key, field, value);
	}

	@Override
	public Long hset(String key, Map<String, String> hash) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.hset(key, hash);
		}
		return jedis.hset(key, hash);
	}

	@Override
	public String hget(String key, String field) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.hget(key, field);
		}
		return jedis.hget(key, field);
	}

	@Override
	public Long hsetnx(String key, String field, String value) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.hsetnx(key, field, value);
		}
		return jedis.hsetnx(key, field, value);
	}

	@Override
	public String hmset(String key, Map<String, String> hash) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.hmset(key, hash);
		}
		return jedis.hmset(key, hash);
	}

	@Override
	public List<String> hmget(String key, String... fields) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.hmget(key, fields);
		}
		return jedis.hmget(key, fields);
	}

	@Override
	public Long hincrBy(String key, String field, long value) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.hincrBy(key, field, value);
		}
		return jedis.hincrBy(key, field, value);
	}

	@Override
	public Double hincrByFloat(String key, String field, double value) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.hincrByFloat(key.getBytes(), field.getBytes(), value);
		}
		return jedis.hincrByFloat(key, field, value);
	}

	@Override
	public Boolean hexists(String key, String field) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.hexists(key, field);
		}
		return jedis.hexists(key, field);
	}

	@Override
	public Long hdel(String key, String... field) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.hdel(key, field);
		}
		return jedis.hdel(key, field);
	}

	@Override
	public Long hlen(String key) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.hlen(key);
		}
		return jedis.hlen(key);
	}

	@Override
	public Set<String> hkeys(String key) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.hkeys(key);
		}
		return jedis.hkeys(key);
	}

	@Override
	public List<String> hvals(String key) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.hvals(key);
		}
		return jedis.hvals(key);
	}

	@Override
	public Map<String, String> hgetAll(String key) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.hgetAll(key);
		}
		return jedis.hgetAll(key);
	}

	@Override
	public Long rpush(String key, String... string) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.rpush(key, string);
		}
		return jedis.rpush(key, string);
	}

	@Override
	public Long lpush(String key, String... string) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.lpush(key, string);
		}
		return jedis.lpush(key, string);
	}

	@Override
	public Long llen(String key) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.llen(key);
		}
		return jedis.llen(key);
	}

	@Override
	public List<String> lrange(String key, long start, long stop) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.lrange(key, start, stop);
		}
		return jedis.lrange(key, start, stop);
	}

	@Override
	public String ltrim(String key, long start, long stop) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.ltrim(key, start, stop);
		}
		return jedis.ltrim(key, start, stop);
	}

	@Override
	public String lindex(String key, long index) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.lindex(key, index);
		}
		return jedis.lindex(key, index);
	}

	@Override
	public String lset(String key, long index, String value) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.lset(key, index, value);
		}
		return jedis.lset(key, index, value);
	}

	@Override
	public Long lrem(String key, long count, String value) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.lrem(key, count, value);
		}
		return jedis.lrem(key, count, value);
	}

	@Override
	public String lpop(String key) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.lpop(key);
		}
		return jedis.lpop(key);
	}

	@Override
	public String rpop(String key) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.rpop(key);
		}
		return jedis.rpop(key);
	}

	@Override
	public Long sadd(String key, String... member) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.sadd(key, member);
		}
		return jedis.sadd(key, member);
	}

	@Override
	public Set<String> smembers(String key) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.smembers(key);
		}
		return jedis.smembers(key);
	}

	@Override
	public Long srem(String key, String... member) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.srem(key, member);
		}
		return jedis.srem(key, member);
	}

	@Override
	public String spop(String key) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.spop(key);
		}
		return jedis.spop(key);
	}

	@Override
	public Set<String> spop(String key, long count) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.spop(key, count);
		}
		return jedis.spop(key, count);
	}

	@Override
	public Long scard(String key) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.scard(key);
		}
		return jedis.scard(key);
	}

	@Override
	public Boolean sismember(String key, String member) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.sismember(key, member);
		}
		return jedis.sismember(key, member);
	}

	@Override
	public String srandmember(String key) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.srandmember(key);
		}
		return jedis.srandmember(key);
	}

	@Override
	public List<String> srandmember(String key, int count) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.srandmember(key, count);
		}
		return jedis.srandmember(key, count);
	}

	@Override
	public Long strlen(String key) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.strlen(key);
		}
		return jedis.strlen(key);
	}

	@Override
	public Long zadd(String key, double score, String member) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zadd(key, score, member);
		}
		return jedis.zadd(key, score, member);
	}

	@Override
	public Long zadd(String key, double score, String member, ZAddParams params) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zadd(key, score, member, params);
		}
		return jedis.zadd(key, score, member, params);
	}

	@Override
	public Long zadd(String key, Map<String, Double> scoreMembers) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zadd(key, scoreMembers);
		}
		return jedis.zadd(key, scoreMembers);
	}

	@Override
	public Long zadd(String key, Map<String, Double> scoreMembers, ZAddParams params) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zadd(key, scoreMembers, params);
		}
		return jedis.zadd(key, scoreMembers, params);
	}

	@Override
	public Set<String> zrange(String key, long start, long stop) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zrange(key, start, stop);
		}
		return jedis.zrange(key, start, stop);
	}

	@Override
	public Long zrem(String key, String... members) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zrem(key, members);
		}
		return jedis.zrem(key, members);
	}

	@Override
	public Double zincrby(String key, double increment, String member) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zincrby(key, increment, member);
		}
		return jedis.zincrby(key, increment, member);
	}

	@Override
	public Double zincrby(String key, double increment, String member, ZIncrByParams params) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zincrby(key, increment, member, params);
		}
		return jedis.zincrby(key, increment, member, params);
	}

	@Override
	public Long zrank(String key, String member) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zrank(key, member);
		}
		return jedis.zrank(key, member);
	}

	@Override
	public Long zrevrank(String key, String member) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zrevrank(key, member);
		}
		return jedis.zrevrank(key, member);
	}

	@Override
	public Set<String> zrevrange(String key, long start, long stop) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zrevrange(key, start, stop);
		}
		return jedis.zrevrange(key, start, stop);
	}

	@Override
	public Set<Tuple> zrangeWithScores(String key, long start, long stop) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zrangeWithScores(key, start, stop);
		}
		return jedis.zrangeWithScores(key, start, stop);
	}

	@Override
	public Set<Tuple> zrevrangeWithScores(String key, long start, long stop) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zrevrangeWithScores(key, start, stop);
		}
		return jedis.zrevrangeWithScores(key, start, stop);
	}

	@Override
	public Long zcard(String key) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zcard(key);
		}
		return jedis.zcard(key);
	}

	@Override
	public Double zscore(String key, String member) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zscore(key, member);
		}
		return jedis.zscore(key, member);
	}

	@Override
	public List<String> sort(String key) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.sort(key);
		}
		return jedis.sort(key);
	}

	@Override
	public List<String> sort(String key, SortingParams sortingParameters) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.sort(key, sortingParameters);
		}
		return jedis.sort(key, sortingParameters);
	}

	@Override
	public Long zcount(String key, double min, double max) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zcount(key, min, max);
		}
		return jedis.zcount(key, min, max);
	}

	@Override
	public Long zcount(String key, String min, String max) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zcount(key, min, max);
		}
		return jedis.zcount(key, min, max);
	}

	@Override
	public Set<String> zrangeByScore(String key, double min, double max) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zrangeByScore(key, min, max);
		}
		return jedis.zrangeByScore(key, min, max);
	}

	@Override
	public Set<String> zrangeByScore(String key, String min, String max) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zrangeByScore(key, min, max);
		}
		return jedis.zrangeByScore(key, min, max);
	}

	@Override
	public Set<String> zrevrangeByScore(String key, double max, double min) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zrevrangeByScore(key, max, min);
		}
		return jedis.zrevrangeByScore(key, max, min);
	}

	@Override
	public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zrangeByScore(key, min, max);
		}
		return jedis.zrangeByScore(key, min, max);
	}

	@Override
	public Set<String> zrevrangeByScore(String key, String max, String min) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zrevrangeByScore(key, max, min);
		}
		return jedis.zrevrangeByScore(key, max, min);
	}

	@Override
	public Set<String> zrangeByScore(String key, String min, String max, int offset, int count) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zrangeByScore(key, min, max);
		}
		return jedis.zrangeByScore(key, min, max);
	}

	@Override
	public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zrevrangeByScore(key, max, min);
		}
		return jedis.zrevrangeByScore(key, max, min);
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zrangeByScoreWithScores(key, min, max);
		}
		return jedis.zrangeByScoreWithScores(key, min, max);
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zrevrangeByScoreWithScores(key, max, min);
		}
		return jedis.zrevrangeByScoreWithScores(key, max, min);
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zrangeByScoreWithScores(key, min, max, offset, count);
		}
		return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
	}

	@Override
	public Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zrevrangeByScore(key, max, min, offset, count);
		}
		return jedis.zrevrangeByScore(key, max, min, offset, count);
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zrangeByScoreWithScores(key, min, max);
		}
		return jedis.zrangeByScoreWithScores(key, min, max);
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zrevrangeByScoreWithScores(key, max, min);
		}
		return jedis.zrevrangeByScoreWithScores(key, max, min);
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max, int offset, int count) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zrangeByScoreWithScores(key, min, max, offset, count);
		}
		return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, double max,
			double min, int offset, int count) {
		if(jedisCluster != null) {
			return jedisCluster.zrevrangeByScoreWithScores(key, max, min, offset, count);
		}
		return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zrevrangeByScoreWithScores(key, max, min, offset, count);
		}
		return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
	}

	@Override
	public Long zremrangeByRank(String key, long start, long stop) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zremrangeByRank(key, start, stop);
		}
		return jedis.zremrangeByRank(key, start, stop);
	}

	@Override
	public Long zremrangeByScore(String key, double min, double max) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zremrangeByScore(key, min, max);
		}
		return jedis.zremrangeByScore(key, min, max);
	}

	@Override
	public Long zremrangeByScore(String key, String min, String max) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zremrangeByScore(key, min, max);
		}
		return jedis.zremrangeByScore(key, min, max);
	}

	@Override
	public Long zlexcount(String key, String min, String max) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zlexcount(key, min, max);
		}
		return jedis.zlexcount(key, min, max);
	}

	@Override
	public Set<String> zrangeByLex(String key, String min, String max) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zrangeByLex(key, min, max);
		}
		return jedis.zrangeByLex(key, min, max);
	}

	@Override
	public Set<String> zrangeByLex(String key, String min, String max, int offset, int count) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zrangeByLex(key, min, max);
		}
		return jedis.zrangeByLex(key, min, max);
	}

	@Override
	public Set<String> zrevrangeByLex(String key, String max, String min) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zrevrangeByLex(key, max, min);
		}
		return jedis.zrevrangeByLex(key, max, min);
	}

	@Override
	public Set<String> zrevrangeByLex(String key, String max, String min, int offset, int count) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zrevrangeByLex(key, max, min, offset, count);
		}
		return jedis.zrevrangeByLex(key, max, min, offset, count);
	}

	@Override
	public Long zremrangeByLex(String key, String min, String max) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zremrangeByLex(key, min, max);
		}
		return jedis.zremrangeByLex(key, min, max);
	}

	@Override
	public Long linsert(String key, ListPosition where, String pivot, String value) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.linsert(key, where, pivot, value);
		}
		return jedis.linsert(key, where, pivot, value);
	}

	@Override
	public Long lpushx(String key, String... string) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.lpushx(key, string);
		}
		return jedis.lpushx(key, string);
	}

	@Override
	public Long rpushx(String key, String... string) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.rpushx(key, string);
		}
		return jedis.rpushx(key, string);
	}

	@Override
	public List<String> blpop(int timeout, String key) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.blpop(timeout, key);
		}
		return jedis.blpop(timeout, key);
	}

	@Override
	public List<String> brpop(int timeout, String key) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.brpop(timeout, key);
		}
		return jedis.brpop(timeout, key);
	}

	@Override
	public Long del(String key) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.del(key);
		}
		return jedis.del(key);
	}

	@Override
	public Long unlink(String key) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.unlink(key);
		}
		return jedis.unlink(key);
	}

	@Override
	public String echo(String string) {
		if(jedisCluster != null) {
			return jedisCluster.echo(string);
		}
		return jedis.echo(string);
	}

	@Override
	public Long move(String key, int dbIndex) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			throw new CommonRuntimeException("没有相关实现");
		}
		return jedis.move(key, dbIndex);
	}

	@Override
	public Long bitcount(String key) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.bitcount(key);
		}
		return jedis.bitcount(key);
	}

	@Override
	public Long bitcount(String key, long start, long end) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.bitcount(key, start, end);
		}
		return jedis.bitcount(key, start, end);
	}

	@Override
	public Long bitpos(String key, boolean value) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			throw new CommonRuntimeException("没有相关实现");
		}
		return jedis.bitpos(key, value);
	}

	@Override
	public Long bitpos(String key, boolean value, BitPosParams params) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			throw new CommonRuntimeException("没有相关实现");
		}
		return jedis.bitpos(key, value, params);
	}

	@Override
	public ScanResult<Entry<String, String>> hscan(String key, String cursor) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.hscan(key, cursor);
		}
		return jedis.hscan(key, cursor);
	}

	@Override
	public ScanResult<Entry<String, String>> hscan(String key, String cursor, ScanParams params) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			throw new CommonRuntimeException("没有相关实现");
		}
		return jedis.hscan(key, cursor, params);
	}

	@Override
	public ScanResult<String> sscan(String key, String cursor) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.sscan(key, cursor);
		}
		return jedis.sscan(key, cursor);
	}

	@Override
	public ScanResult<Tuple> zscan(String key, String cursor) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.zscan(key, cursor);
		}
		return jedis.zscan(key, cursor);
	}

	@Override
	public ScanResult<Tuple> zscan(String key, String cursor, ScanParams params) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			throw new CommonRuntimeException("没有相关实现");
		}
		return jedis.zscan(key, cursor, params);
	}

	@Override
	public ScanResult<String> sscan(String key, String cursor, ScanParams params) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			throw new CommonRuntimeException("没有相关实现");
		}
		return jedis.sscan(key, cursor, params);
	}

	@Override
	public Long pfadd(String key, String... elements) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.pfadd(key, elements);
		}
		return jedis.pfadd(key, elements);
	}

	@Override
	public long pfcount(String key) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.pfcount(key);
		}
		return jedis.pfcount(key);
	}

	@Override
	public Long geoadd(String key, double longitude, double latitude, String member) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.geoadd(key, longitude, latitude, member);
		}
		return jedis.geoadd(key, longitude, latitude, member);
	}

	@Override
	public Long geoadd(String key, Map<String, GeoCoordinate> memberCoordinateMap) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.geoadd(key, memberCoordinateMap);
		}
		return jedis.geoadd(key, memberCoordinateMap);
	}

	@Override
	public Double geodist(String key, String member1, String member2) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.geodist(key, member1, member2);
		}
		return jedis.geodist(key, member1, member2);
	}

	@Override
	public Double geodist(String key, String member1, String member2, GeoUnit unit) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.geodist(key, member1, member2, unit);
		}
		return jedis.geodist(key, member1, member2, unit);
	}

	@Override
	public List<String> geohash(String key, String... members) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.geohash(key, members);
		}
		return jedis.geohash(key, members);
	}

	@Override
	public List<GeoCoordinate> geopos(String key, String... members) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.geopos(key, members);
		}
		return jedis.geopos(key, members);
	}

	@Override
	public List<GeoRadiusResponse> georadius(String key, double longitude,
			double latitude, double radius, GeoUnit unit) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.georadius(key, longitude, latitude, radius, unit);
		}
		return jedis.georadius(key, longitude, latitude, radius, unit);
	}

	@Override
	public List<GeoRadiusResponse> georadiusReadonly(String key,
			double longitude, double latitude, double radius, GeoUnit unit) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.georadiusReadonly(key, longitude, latitude, radius, unit);
		}
		return jedis.georadiusReadonly(key, longitude, latitude, radius, unit);
	}

	@Override
	public List<GeoRadiusResponse> georadius(String key, double longitude,
			double latitude, double radius, GeoUnit unit, GeoRadiusParam param) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.georadius(key, longitude, latitude, radius, unit, param);
		}
		return jedis.georadius(key, longitude, latitude, radius, unit, param);
	}

	@Override
	public List<GeoRadiusResponse> georadiusReadonly(String key, double longitude,
													 double latitude, double radius, GeoUnit unit,GeoRadiusParam param) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.georadiusReadonly(key, longitude, latitude, radius, unit, param);
		}
		return jedis.georadiusReadonly(key, longitude, latitude, radius, unit, param);
	}

	@Override
	public List<GeoRadiusResponse> georadiusByMember(String key, String member,
			double radius, GeoUnit unit) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.georadiusByMember(key, member, radius, unit);
		}
		return jedis.georadiusByMember(key, member, radius, unit);
	}

	@Override
	public List<GeoRadiusResponse> georadiusByMemberReadonly(String key,
			String member, double radius, GeoUnit unit) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.georadiusByMemberReadonly(key, member, radius, unit);
		}
		return jedis.georadiusByMemberReadonly(key, member, radius, unit);
	}

	@Override
	public List<GeoRadiusResponse> georadiusByMember(String key, String member,
			double radius, GeoUnit unit, GeoRadiusParam param) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.georadiusByMember(key, member, radius, unit, param);
		}
		return jedis.georadiusByMember(key, member, radius, unit, param);
	}

	@Override
	public List<GeoRadiusResponse> georadiusByMemberReadonly(String key,
			String member, double radius, GeoUnit unit, GeoRadiusParam param) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.georadiusByMemberReadonly(key, member, radius, unit, param);
		}
		return jedis.georadiusByMemberReadonly(key, member, radius, unit, param);
	}

	@Override
	public List<Long> bitfield(String key, String... arguments) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.bitfield(key, arguments);
		}
		return jedis.bitfield(key, arguments);
	}

	@Override
	public Long hstrlen(String key, String field) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.hstrlen(key, field);
		}
		return jedis.hstrlen(key, field);
	}

	@Override
	public StreamEntryID xadd(String key, StreamEntryID id,
			Map<String, String> hash) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.xadd(key, id, hash);
		}
		return jedis.xadd(key, id, hash);
	}

	@Override
	public StreamEntryID xadd(String key, StreamEntryID id,
			Map<String, String> hash, long maxLen, boolean approximateLength) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.xadd(key, id, hash, maxLen, approximateLength);
		}
		return jedis.xadd(key, id, hash, maxLen, approximateLength);
	}

	@Override
	public Long xlen(String key) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.xlen(key);
		}
		return jedis.xlen(key);
	}

	@Override
	public List<StreamEntry> xrange(String key, StreamEntryID start,
			StreamEntryID end, int count) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.xrange(key, start, end, count);
		}
		return jedis.xrange(key, start, end, count);
	}

	@Override
	public List<StreamEntry> xrevrange(String key, StreamEntryID end,
			StreamEntryID start, int count) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.xrevrange(key, end, start, count);
		}
		return jedis.xrevrange(key, end, start, count);
	}

	@Override
	public long xack(String key, String group, StreamEntryID... ids) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.xack(key, group, ids);
		}
		return jedis.xack(key, group, ids);
	}

	@Override
	public String xgroupCreate(String key, String groupname, StreamEntryID id,
			boolean makeStream) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.xgroupCreate(key, groupname, id, makeStream);
		}
		return jedis.xgroupCreate(key, groupname, id, makeStream);
	}

	@Override
	public String xgroupSetID(String key, String groupname, StreamEntryID id) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.xgroupSetID(key, groupname, id);
		}
		return jedis.xgroupSetID(key, groupname, id);
	}

	@Override
	public long xgroupDestroy(String key, String groupname) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.xgroupDestroy(key, groupname);
		}
		return jedis.xgroupDestroy(key, groupname);
	}

	@Override
	public Long xgroupDelConsumer(String key, String groupname,
			String consumername) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.xgroupDelConsumer(key, groupname, consumername);
		}
		return jedis.xgroupDelConsumer(key, groupname, consumername);
	}

	@Override
	public List<StreamPendingEntry> xpending(String key, String groupname,
			StreamEntryID start, StreamEntryID end, int count,
			String consumername) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.xpending(key, groupname, start, end, count, consumername);
		}
		return jedis.xpending(key, groupname, start, end, count, consumername);
	}

	@Override
	public long xdel(String key, StreamEntryID... ids) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.xdel(key, ids);
		}
		return jedis.xdel(key, ids);
	}

	@Override
	public long xtrim(String key, long maxLen, boolean approximate) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.xtrim(key, maxLen, approximate);
		}
		return jedis.xtrim(key, maxLen, approximate);
	}

	@Override
	public List<StreamEntry> xclaim(String key, String group,
			String consumername, long minIdleTime, long newIdleTime,
			int retries, boolean force, StreamEntryID... ids) {
		key = prepostKey(key);
		if(jedisCluster != null) {
			return jedisCluster.xclaim(key, group, consumername, minIdleTime, newIdleTime, retries, force, ids);
		}
		return jedis.xclaim(key, group, consumername, minIdleTime, newIdleTime, retries, force, ids);
	}
	
	@Override
	public List<Long> bitfieldReadonly(String arg0, String... arg1) {
		arg0 = prepostKey(arg0);
		if(jedisCluster != null) {
			return jedisCluster.bitfieldReadonly(arg0,arg1);
		}
		return jedis.bitfieldReadonly(arg0,arg1);
	}

	@Override
	public List<StreamConsumersInfo> xinfoConsumers(String arg0, String arg1) {
		arg0 = prepostKey(arg0);
		if(jedisCluster != null) {
			throw new CommonRuntimeException("没有相关实现");
		}
		return jedis.xinfoConsumers(arg0,arg1);
	}

	@Override
	public List<StreamGroupInfo> xinfoGroup(String arg0) {
		arg0 = prepostKey(arg0);
		if(jedisCluster != null) {
			throw new CommonRuntimeException("没有相关实现");
		}
		return jedis.xinfoGroup(arg0);
	}

	@Override
	public StreamInfo xinfoStream(String arg0) {
		arg0 = prepostKey(arg0);
		if(jedisCluster != null) {
			throw new CommonRuntimeException("没有相关实现");
		}
		return jedis.xinfoStream(arg0);
	}

	@Override
	public Tuple zpopmax(String arg0) {
		arg0 = prepostKey(arg0);
		if(jedisCluster != null) {
			return jedisCluster.zpopmax(arg0);
		}
		return jedis.zpopmax(arg0);
	}

	@Override
	public Set<Tuple> zpopmax(String arg0, int arg1) {
		arg0 = prepostKey(arg0);
		if(jedisCluster != null) {
			return jedisCluster.zpopmax(arg0,arg1);
		}
		return jedis.zpopmax(arg0,arg1);
	}

	@Override
	public Tuple zpopmin(String arg0) {
		arg0 = prepostKey(arg0);
		if(jedisCluster != null) {
			return jedisCluster.zpopmin(arg0);
		}
		return jedis.zpopmin(arg0);
	}

	@Override
	public Set<Tuple> zpopmin(String arg0, int arg1) {
		arg0 = prepostKey(arg0);
		if(jedisCluster != null) {
			return jedisCluster.zpopmin(arg0,arg1);
		}
		return jedis.zpopmin(arg0,arg1);
	}
	
	public void close() {
		for(int key : mapPipeline.keySet()) {
			try{
				mapPipeline.get(key).close();
			}catch (Exception e) {
			}
		}
		for(Jedis j : liJedis) {
			try{
				j.close();
			}catch (Exception e) {
			}
		}
		liJedis.clear();
		mapPipeline.clear();
	}
	
	public Pipeline pipeline(String key) {
		key = prepostKey(key);
		if(jedis != null) {
			Pipeline pipeline = mapPipeline.get(-1);
			if(pipeline == null) {
				pipeline = jedis.pipelined();
				mapPipeline.put(-1, pipeline);
			}
			return pipeline;
		}
		try{
			int slot = JedisClusterUtil.getSlot(key);
			Pipeline pipeline = mapPipeline.get(slot);
			if(pipeline == null) {
				if(jedisCluster != null) {
					Jedis tmpJedis = JedisClusterUtil.getJedisBySlot(jedisCluster, slot);
					pipeline = tmpJedis.pipelined();
					mapPipeline.put(slot, pipeline);
					liJedis.add(tmpJedis);
				}
			}
			return pipeline;
		}catch(Exception e) {
			close();
			throw e;
		}
		
	}
	
	public void pipelineSync() {
		if(jedis != null) {
			Pipeline pipeline = mapPipeline.get(-1);
			if(pipeline != null) {
				pipeline.sync();
				mapPipeline.remove(-1);
			}
			return;
		}
		try{
			for(int key : mapPipeline.keySet()) {
				Pipeline pipeline = mapPipeline.get(key);
				pipeline.sync();
			}
		}catch(Exception e) {
			close();
			throw e;
		}
		
	}
}
