package com.zccoder.demo.service.user.api;

import com.zccoder.demo.service.user.bo.ListReqBo;
import com.zccoder.demo.service.user.bo.ListRspBo;
import com.zccoder.demo.service.user.bo.SaveAndHelloReqBo;
import com.zccoder.demo.service.user.bo.SaveAndHelloRspBo;

/**
 * @title 用户dubbo服务接口类
 * @describe 用户相关信息
 * @author zc
 * @version 1.0 2017-09-15
 */
public interface UserService {
	
	/**
	 * @describe 保存用户信息并对用户名说Hello
	 * @author zc
	 * @version 1.0 2017-09-15
	 */
	SaveAndHelloRspBo saveAndHello(SaveAndHelloReqBo saveAndHelloReqBo);
	
	/**
	 * @describe 获取所有用户
	 * @author zc
	 * @version 1.0 2017-09-17
	 */
	ListRspBo list(ListReqBo listReqBo);
	
}
