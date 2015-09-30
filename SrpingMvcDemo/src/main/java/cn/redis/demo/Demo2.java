package cn.redis.demo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.redis.model.Product;
import cn.redis.util.Executors;

public class Demo2 {
	//配置name进行注入 加载配置文件使用new Stringp[]{}
	@Test
	public void test1(){
		ApplicationContext acc = new ClassPathXmlApplicationContext(new String [] {"applicationContext.xml"});
		Product product = acc.getBean(Product.class);
		product.setName("ssss");
		System.out.println("product's name :"+product.getName());
	}
	//destroy-method
	@Test
	public void test2() throws InterruptedException{
		ApplicationContext acc = new ClassPathXmlApplicationContext(new String [] {"applicationContext.xml"});
		Executors executors = acc.getBean(Executors.class);
		System.out.println(executors);
		executors = null;
		System.gc();
	}
	
}
