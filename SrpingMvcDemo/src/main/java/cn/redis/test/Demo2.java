package cn.redis.test;

import java.util.ResourceBundle;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis 池化
 * 
 * @author Administrator
 *
 */
public class Demo2 {
	private static JedisPool pool;
	static {
		ResourceBundle bundle = ResourceBundle.getBundle("redis");
		if (bundle == null) {
			throw new IllegalArgumentException(
					"[redis.properties] is not found!");
		}
		JedisPoolConfig config = new JedisPoolConfig();
		
		config.setMaxActive(Integer.valueOf(bundle
				.getString("redis.pool.maxActive")));
		config.setMaxIdle(Integer.valueOf(bundle
				.getString("redis.pool.maxIdle")));
		config.setMaxWait(Long.valueOf(bundle.getString("redis.pool.maxWait")));
		config.setTestOnBorrow(Boolean.valueOf(bundle
				.getString("redis.pool.testOnBorrow")));
		config.setTestOnReturn(Boolean.valueOf(bundle
				.getString("redis.pool.testOnReturn")));
		pool = new JedisPool(config, bundle.getString("redis.ip"),
				Integer.valueOf(bundle.getString("redis.port")));
		
	}
	
	Jedis jedis = pool.getResource(); 
	
	@Test
	public void addTest(){
		addValue("myName","wangqq");
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
