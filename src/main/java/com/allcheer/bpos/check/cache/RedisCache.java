package com.allcheer.bpos.check.cache;

import com.allcheer.bpos.check.CacheSuper;
import com.allcheer.bpos.constant.SystemConstant;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Map;

@Component("cache")
public class RedisCache implements CacheSuper {

	private static int TIME_OUT = 200 * 1000; // 单位毫秒

	static JedisPool pool = null;

	static {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(500); // 最多连接数，-1不限制
		config.setMaxIdle(5); // 最多空闲连接数
		config.setMaxWaitMillis(1000 * 100); // 等待空闲连接时间，-1不超时
		config.setTestOnBorrow(true); // 检测连接可用性，即返回的都可用
		pool = new JedisPool(config, SystemConstant.IP_SERVICE, Integer.parseInt(SystemConstant.PORT_SERVICE),
				TIME_OUT);
	}

	public synchronized Jedis getJedis() {
		try {
			if (pool != null) {
				Jedis resource = pool.getResource();
				return resource;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean batchPut(String key, Map<String, String> value) {
		Jedis jedis = getJedis();
		if (jedis != null) {
			jedis.hmset(key, value);
			pool.returnResource(jedis);
			return true;
		}
		pool.returnResource(jedis);
		return false;
	}

	@Override
	public boolean dorp() {
		Jedis jedis = getJedis();
		String flushDB = jedis.flushDB();
		pool.returnResource(jedis);
		if (flushDB == null) {
			return false;
		}
		return true;
	}

	@Override
	public String getMapValue(String name, String key) {
		Jedis jedis = getJedis();
		List<String> list = jedis.hmget(name, key);
		pool.returnResource(jedis);
		return list.get(0);
	}

}
