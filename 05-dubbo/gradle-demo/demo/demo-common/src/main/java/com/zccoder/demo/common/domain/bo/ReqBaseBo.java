package com.zccoder.demo.common.domain.bo;

import java.io.Serializable;

/**
 * @title 基础业务模型类
 * @describe 所有的业务方法的入参Bo需继承该类
 * @author zc
 * @version 1.0 2017-09-15
 */
public class ReqBaseBo implements Serializable{

	private static final long serialVersionUID = 1L;
	/** 内部接口流转标识 */
	private String transId;
	
	@Override
	public String toString() {
		return "ReqBaseBo [transId=" + transId + "]";
	}
	
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
}
