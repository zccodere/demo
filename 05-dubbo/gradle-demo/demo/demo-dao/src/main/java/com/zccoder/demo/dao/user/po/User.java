package com.zccoder.demo.dao.user.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @title 用户实体类
 * @describe 用户相关信息
 * @author zc
 * @version 1.0 2017-09-15
 */
@Entity
public class User {
	/** 编号 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
