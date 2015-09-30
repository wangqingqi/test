package cn.redis.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("serviceDemo")
@Service
public interface ServiceDemo {
	public void run();
}
