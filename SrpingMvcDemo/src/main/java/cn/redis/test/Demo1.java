package cn.redis.test;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class Demo1 {

	Jedis jedis = new Jedis("10.10.4.40");

	/**
	 * 存数据
	 */
	@Test
	public void addTest() {
		addValue("pass", "ok");
	}

	@Test
	public void delTest() {
		delValue("pass");
	}

	@Test
	public void getTest() {
		System.out.println(getValue("pass"));

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
