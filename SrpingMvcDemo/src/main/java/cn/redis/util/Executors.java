package cn.redis.util;

import org.springframework.stereotype.Component;

@Component("Executors")
public class Executors {

	public static String shutdown() {
		System.out.println("shutdown方法");
		return null;
	}

	public  static  Executors	 newCachedThreadPool(){
		return new Executors();
	}

	public Executors(){
		System.out.println("被初始化");
	}
	public void finalize() {
		System.out.println("我已经被垃圾回收器回收了...");
	}
}
