package com.zccoder.demo.service.user.bo;

import com.zccoder.demo.common.domain.bo.ReqBaseBo;

/**
 * @title 获取所有用户信息入参Bo
 * @describe 类UserService.list方法入参Bo
 * @author zc
 * @version 1.0 2017-09-17
 */
public class ListReqBo extends ReqBaseBo{

	private static final long serialVersionUID = 1L;
	
	/** 可选  根据用户名进行模糊匹配查询 */
	private String name;
	/** 可选  根据登录密码匹配查询 */
	private String password;
	
	@Override
	public String toString() {
		// 当有父类时，先调父类的toString方法
		super.toString();
		return "ListReqBo [name=" + name + ", password=" + password + "]";
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
