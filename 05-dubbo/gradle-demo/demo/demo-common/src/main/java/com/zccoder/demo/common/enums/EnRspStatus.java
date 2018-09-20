package com.zccoder.demo.common.enums;

/**
 * @title 业务方法响应结果枚举类
 * @describe 业务方法响应结果 RspBaseBo类rspStatus属性对应可选枚举对象
 * @author zc
 * @version 1.0 2017-09-15
 */
public enum EnRspStatus {
	
	SUCCESS("0000","成功"),
	EXCEPTION_SYSTEM("1001","系统异常"),
	EXCEPTION_BUSINESS("1002","业务异常"),
	ERROR_SYSTEM("1003","系统错误");
	
	/** 状态码 */
	private String code;
	/** 状态描述 */
	private String desc;
	
	private EnRspStatus(String code,String desc){
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
