package cn.redis.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * 通过封装spring进行redis连接
 * 
 * @author Administrator
 *
 */
public class Demo4 {
	private ApplicationContext app;
	private ShardedJedisPool pool;
	private ShardedJedis jedis;

	@Before
	public void before() throws Exception {
		app = new ClassPathXmlApplicationContext("applicationContext.xml");
		pool = (ShardedJedisPool) app.getBean("shardedJedisPool");
		jedis = pool.getResource();
	}

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
