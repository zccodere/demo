package com.zccoder.demo.service.user.bo;

import com.zccoder.demo.common.domain.bo.RspBaseBo;

/**
 * @title 保存用户信息并对用户名说Hello出参Bo
 * @describe 类UserService.saveAndHello方法出参Bo
 * @author zc
 * @version 1.0 2017-09-15
 */
public class SaveAndHelloRspBo extends RspBaseBo {

	private static final long serialVersionUID = 1L;
	
	/** 用户ID，方法执行成功时返回 */
	private String id;
	/** Hello信息，方法执行成功时返回 */
	private String info;
	
	@Override
	public String toString() {
		super.toString();
		return "SaveAndHelloRspBo [id=" + id + ", info=" + info + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
}
