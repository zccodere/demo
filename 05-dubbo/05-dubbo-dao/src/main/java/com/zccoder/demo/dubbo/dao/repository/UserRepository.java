package com.zccoder.demo.dubbo.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.zccoder.demo.dubbo.dao.po.User;
import org.springframework.stereotype.Repository;

/**
 * @author zc
 * @version 1.0 2017-09-15
 * @title 用户资源类
 * @describe 用户相关数据库操作
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {


}
