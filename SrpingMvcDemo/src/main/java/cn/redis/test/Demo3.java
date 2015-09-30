package cn.redis.test;

import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import org.junit.Test;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * Memcached完全基于分布式集群，而Redis是Master-Slave，如果想把Reids，做成集群模式，无外乎多做几套Master-Slave，
 * 每套Master-Slave完成各自的容灾处理，通过Client工具，完成一致性哈希。
 * PS:Memcached是在Server端完成Sharding，Redis只能依靠各个Client做Sharding。可能会在Redis
 * 3.0系列支持Server端Sharding。
 * 保留前面的JedisPoolConfig，新增两个Redis的IP（redis1.ip，redis2.ip）
 * ，完成两个JedisShardInfo实例，并将其丢进List中：
 * @author Administrator
 *
 */
/**
 * redis的集群是配置多个redis连接，对有空闲连接的ip插入数据。并不是同时多个redis进行插入
 * 
 * @author Administrator
 *
 */
public class Demo3 {
	private static final List<JedisShardInfo> list = new LinkedList<JedisShardInfo>();
	private static final JedisPoolConfig config = new JedisPoolConfig();

	static {
		ResourceBundle bundle = ResourceBundle.getBundle("redis");
		if (bundle == null) {
			throw new IllegalArgumentException(
					"[redis.properties] is not found!");
		}
		JedisShardInfo jedisShardInfo1 = new JedisShardInfo(
				bundle.getString("redis1.ip"), Integer.valueOf(bundle
						.getString("redis.port")));
		JedisShardInfo jedisShardInfo2 = new JedisShardInfo(
				bundle.getString("redis2.ip"), Integer.valueOf(bundle
						.getString("redis.port")));
		list.add(jedisShardInfo1);
		list.add(jedisShardInfo2);

		config.setMaxActive(Integer.valueOf(bundle
				.getString("redis.pool.maxActive")));
		config.setMaxIdle(Integer.valueOf(bundle
				.getString("redis.pool.maxIdle")));
		config.setMaxWait(Long.valueOf(bundle.getString("redis.pool.maxWait")));
		config.setTestOnBorrow(Boolean.valueOf(bundle
				.getString("redis.pool.testOnBorrow")));
		config.setTestOnReturn(Boolean.valueOf(bundle
				.getString("redis.pool.testOnReturn")));
	}

	ShardedJedisPool pool = new ShardedJedisPool(config, list);
	ShardedJedis jedis = pool.getResource();

	@Test
	public void addTest() {
		addValue("myName", "wangqq");
		pool.returnResource(jedis);
	}

	@Test
	public void delTest() {
		delValue("myName");
		pool.returnResource(jedis);
	}

	@Test
	public void getTest() {
		System.out.println(getValue("myName"));
		pool.returnResource(jedis);
	}

	/**
	 * 添加数据的方法
	 * 
	 * @param keys
	 * @param value
	 */
	public void addValue(String keys, String value) {

		jedis.set(keys, value);
	}

	/**
	 * 删除数据
	 * 
	 * @param keys
	 */
	public void delValue(String keys) {

		jedis.del(keys);
	}

	/**
	 * 根据key查询数据
	 * 
	 * @param keys
	 * @return
	 */
	public String getValue(String keys) {

		return jedis.get(keys);
	}
}
