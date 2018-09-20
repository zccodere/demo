package com.zccoder.demo.service.user.bo;

import java.io.Serializable;

/**
 * @title 获取所有用户信息出参Bo
 * @describe 类UserService.list方法出参Bo
 * @author zc
 * @version 1.0 2017-09-17
 */
public class UserBo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	private Long id;
	/** 用户名 */
	private String name;
	/** 登录密码 */
	private String password;
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + "]";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}