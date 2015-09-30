package cn.redis.service.iml;

import org.springframework.stereotype.Service;

import cn.redis.service.ServiceDemo;
@Service("ServiceDemoIml1")
public class ServiceDemoIml1 implements ServiceDemo{

	public void run() {
		System.out.println("hello1");
	}

}
