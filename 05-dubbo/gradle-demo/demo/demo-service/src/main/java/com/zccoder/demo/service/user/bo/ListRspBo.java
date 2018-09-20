package com.zccoder.demo.service.user.bo;

import java.util.List;

import com.zccoder.demo.common.domain.bo.RspBaseBo;

/**
 * @title 获取所有用户信息出参Bo
 * @describe 类UserService.list方法出参Bo
 * @author zc
 * @version 1.0 2017-09-17
 */
public class ListRspBo extends RspBaseBo{

	private static final long serialVersionUID = 1L;
	
	/** 用户信息集合，当查询数据为空时，返回空集合 */
	private List<UserBo> users;

	@Override
	public String toString() {
		return "ListRspBo [users=" + String.valueOf(users) + "]";
	}

	public List<UserBo> getUsers() {
		return users;
	}

	public void setUsers(List<UserBo> users) {
		this.users = users;
	}
}
