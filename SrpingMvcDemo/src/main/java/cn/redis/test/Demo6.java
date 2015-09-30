package cn.redis.test;

import org.junit.Test;

import cn.redis.util.RedisUtil;

public class Demo6 {
	@Test
	public void test1(){
		RedisUtil redisUtil = new RedisUtil("10.10.4.40",6379,1000);
		
		System.out.println(redisUtil.hget("SUB_BILL_LOG", "680000000004"));
	
	}
	
}
