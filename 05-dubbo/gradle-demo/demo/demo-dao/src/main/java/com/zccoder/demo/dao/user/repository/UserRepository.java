package com.zccoder.demo.dao.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.zccoder.demo.dao.user.po.User;

/**
 * @title 用户资源类
 * @describe 用户相关数据库操作
 * @author zc
 * @version 1.0 2017-09-15
 */
public interface UserRepository extends JpaRepository<User, Long>,JpaSpecificationExecutor<User> {
	
	
}
