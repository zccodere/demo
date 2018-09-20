package com.zccoder.demo.common.domain.bo;

import java.io.Serializable;

import com.zccoder.demo.common.enums.EnRspStatus;

/**
 * @title 基础业务模型类
 * @describe 所有的业务方法的出参Bo需继承该类
 * @author zc
 * @version 1.0 2017-09-15
 */
public class RspBaseBo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/** 方法处理响应结果 */
	private EnRspStatus rspStatus;
	
	@Override
	public String toString() {
		return "RspBaseBo [rspStatus=" + String.valueOf(rspStatus.getCode()+" "+rspStatus.getDesc()) + "]";
	}
	public RspBaseBo() {
		
	}
	public RspBaseBo(EnRspStatus rspStatus) {
		this.rspStatus = rspStatus;
	}

	public EnRspStatus getRspStatus() {
		return rspStatus;
	}
	public void setRspStatus(EnRspStatus rspStatus) {
		this.rspStatus = rspStatus;
	}
	
}
