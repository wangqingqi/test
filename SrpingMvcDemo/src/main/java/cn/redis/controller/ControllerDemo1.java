package cn.redis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerDemo1 {
	
	@RequestMapping("/welcome")
	public String index(){
		return "index";
	}
	
	
	
}
