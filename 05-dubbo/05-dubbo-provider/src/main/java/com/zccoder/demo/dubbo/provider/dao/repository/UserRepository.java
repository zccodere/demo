package com.zccoder.demo.dubbo.provider.dao.repository;

import com.zccoder.demo.dubbo.provider.dao.po.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 用户资源类
 *
 * @author zc 2017-09-15
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {


}
