package com.zccoder.demo.rest.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zccoder.demo.service.user.api.UserService;
import com.zccoder.demo.service.user.bo.ListReqBo;
import com.zccoder.demo.service.user.bo.ListRspBo;
import com.zccoder.demo.service.user.bo.SaveAndHelloReqBo;
import com.zccoder.demo.service.user.bo.SaveAndHelloRspBo;

@RestController
public class UserRest {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/save")
	public Object save(String name,String password){
		SaveAndHelloReqBo reqBo = new SaveAndHelloReqBo();
		reqBo.setName(name);
		reqBo.setPassword(password);
		SaveAndHelloRspBo rsqBo = userService.saveAndHello(reqBo);
		return rsqBo;
	}
	
	@PostMapping("/list")
	public Object list(String name,String password){
		ListReqBo reqBo = new ListReqBo();
		reqBo.setName(name);
		reqBo.setPassword(password);
		ListRspBo rsqBo = userService.list(reqBo);
		return rsqBo;
	}
}
