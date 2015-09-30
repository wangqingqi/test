package cn.redis.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo7 {
	private static Logger logger = LoggerFactory.getLogger(Demo7.class);	
	
	@Test
	public void test1(){
		String error = "error";
		String warn = "warn";
		logger.debug("this is a debug message");
		logger.info("this is a info message");
		logger.warn("this is a {} message",warn);
		logger.error("this is a {} message",error);
		
		
	}
}
