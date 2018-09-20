package com.zccoder.demo.service.user.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import com.zccoder.demo.common.enums.EnRspStatus;
import com.zccoder.demo.dao.user.po.User;
import com.zccoder.demo.dao.user.repository.UserRepository;
import com.zccoder.demo.service.user.api.UserService;
import com.zccoder.demo.service.user.bo.ListReqBo;
import com.zccoder.demo.service.user.bo.ListRspBo;
import com.zccoder.demo.service.user.bo.SaveAndHelloReqBo;
import com.zccoder.demo.service.user.bo.SaveAndHelloRspBo;
import com.zccoder.demo.service.user.bo.UserBo;

/**
 * @title 用户dubbo服务接口实现类
 * @describe 用户相关信息
 * @author zc
 * @version 1.0 2017-09-15
 */
public class UserServiceImpl implements UserService {
	
	// 使用slf4j框架提供的API
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * @describe 保存用户信息并对用户名说Hello
	 * @author zc
	 * @version 1.0 2017-09-15
	 */
	@Override
	public SaveAndHelloRspBo saveAndHello(SaveAndHelloReqBo saveAndHelloReqBo) {
		logger.info("请求参数：{}",saveAndHelloReqBo.toString());
		SaveAndHelloRspBo saveAndHelloRspBo = new SaveAndHelloRspBo();
		
		User reqUser = new User();
		reqUser.setName(saveAndHelloReqBo.getName());
		reqUser.setPassword(saveAndHelloReqBo.getPassword());
		User rspUser = this.userRepository.save(reqUser);
		
		saveAndHelloRspBo.setId(rspUser.getId().toString());
		saveAndHelloRspBo.setInfo("Hello " + rspUser.getName());
		saveAndHelloRspBo.setRspStatus(EnRspStatus.SUCCESS);
		logger.info("请求响应：{}",saveAndHelloRspBo.toString());
		return saveAndHelloRspBo;
	}
	
	/**
	 * @describe 获取所有用户
	 * @author zc
	 * @version 1.0 2017-09-17
	 */
	@Override
	public ListRspBo list(ListReqBo listReqBo) {
		ListRspBo rspBo = new ListRspBo();
		
		final String name = listReqBo.getName();
		final String password = listReqBo.getPassword();

		// 构建查询条件
		Specification<User> querySpeci = new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> lstPredicates = new ArrayList<Predicate>();
				// 如果用户名 不为空
				if ("" != name && null != name) {
					lstPredicates.add(cb.like(root.get("name").as(String.class), "%" + name + "%"));
				}
				// 如果 密码 不为空
				if ("" != password && null != password) {
					lstPredicates.add(cb.equal(root.get("password").as(String.class), password));
				}
				Predicate[] arrayPredicates = new Predicate[lstPredicates.size()];
				return cb.and(lstPredicates.toArray(arrayPredicates));
			}
		};
		
		// 查找符合条件的记录
		List<User> listProjectPo = this.userRepository.findAll(querySpeci);
		
		if(listProjectPo==null || listProjectPo.size() < 1){
			rspBo.setRspStatus(EnRspStatus.SUCCESS);
			rspBo.setUsers(new ArrayList<UserBo>());
			return rspBo;
		}
		
		List<UserBo> users = new ArrayList<UserBo>();
		
		// 使用java8提供的forEach()方法和Lambda表达式
		listProjectPo.forEach(user -> {
			UserBo userBo = new UserBo();
			BeanUtils.copyProperties(user, userBo);
			users.add(userBo);
		});
		
		rspBo.setRspStatus(EnRspStatus.SUCCESS);
		rspBo.setUsers(users);
		return rspBo;
	}
}
