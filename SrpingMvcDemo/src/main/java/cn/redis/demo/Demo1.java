package cn.redis.demo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.redis.service.ServiceDemo;
import cn.redis.service.iml.ServiceDemoIml1;
import cn.redis.service.iml.ServiceDemoIml2;
import cn.redis.service.iml.ServiceDemoIml3;
//通过id注入
public class Demo1 {
	//不使用spring 进行接口的实现
	@Test
	public void test1(){
		ServiceDemo demo1 = new ServiceDemoIml1();
		ServiceDemo demo2 = new ServiceDemoIml2();
		ServiceDemo demo3 = new ServiceDemoIml3();
		demo1.run();
		demo2.run();
		demo3.run();
	}
	//使用注解实现接口
	@Test
	public void test2(){
		ApplicationContext acc = new ClassPathXmlApplicationContext("applicationContext.xml");
		ServiceDemo  demo = acc.getBean(ServiceDemoIml1.class);
		demo.run();
	}

	//使用xml配置实现接口
	@Test
	public void test3(){
		ApplicationContext acc = new ClassPathXmlApplicationContext("applicationContext.xml");
		ServiceDemo  demo = acc.getBean(ServiceDemoIml2.class);
		demo.run();
	}
	
	//使用多份配置文件实现注入，iml3在config1.xml配置文件中，通过import注入主配置文件中
	@Test
	public void test4(){
		ApplicationContext acc = new ClassPathXmlApplicationContext("applicationContext.xml");
		ServiceDemo  demo = acc.getBean(ServiceDemoIml3.class);
		demo.run();
	}
}
