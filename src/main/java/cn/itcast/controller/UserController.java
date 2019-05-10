package cn.itcast.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.itcast.entity.User;

@CrossOrigin
@RestController
public class UserController {
	// 使用 get 方式提交表单
	@GetMapping("/login")
	public Object login(User user) {
		System.out.println("get:");
		System.out.println(user);
		return user;
	}
	
	// 使用 post 方式提交表单
	@PostMapping("/login")
	public Object login2(User user) {
		System.out.println("post:");
		System.out.println(user);
		return user;
	}
	
	// 使用 put 方式提交表单
	@PutMapping("/login")
	public Object login3(@RequestBody User user) {
		System.out.println("put:");
		System.out.println(user);
		return user;
	}
}
