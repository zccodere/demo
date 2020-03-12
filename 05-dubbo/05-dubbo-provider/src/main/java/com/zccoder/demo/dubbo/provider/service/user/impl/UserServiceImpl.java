package com.zccoder.demo.dubbo.provider.service.user.impl;

import com.zccoder.demo.dubbo.common.enums.ResponseEnum;
import com.zccoder.demo.dubbo.provider.dao.po.User;
import com.zccoder.demo.dubbo.provider.dao.repository.UserRepository;
import com.zccoder.demo.dubbo.api.user.UserService;
import com.zccoder.demo.dubbo.api.user.bo.ListReqBo;
import com.zccoder.demo.dubbo.api.user.bo.ListRspBo;
import com.zccoder.demo.dubbo.api.user.bo.SaveAndHelloReqBo;
import com.zccoder.demo.dubbo.api.user.bo.SaveAndHelloRspBo;
import com.zccoder.demo.dubbo.api.user.bo.UserBo;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.criteria.Predicate;

/**
 * 用户dubbo服务接口实现类
 *
 * @author zc 2017-09-15
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * 使用slf4j框架提供的API
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Override
    public SaveAndHelloRspBo saveAndHello(SaveAndHelloReqBo saveAndHelloReqBo) {
        logger.info("请求参数：{}", saveAndHelloReqBo.toString());
        SaveAndHelloRspBo saveAndHelloRspBo = new SaveAndHelloRspBo();

        User reqUser = new User();
        reqUser.setName(saveAndHelloReqBo.getName());
        reqUser.setPassword(saveAndHelloReqBo.getPassword());
        User rspUser = this.userRepository.save(reqUser);

        saveAndHelloRspBo.setId(rspUser.getId().toString());
        saveAndHelloRspBo.setInfo("Hello " + rspUser.getName());
        saveAndHelloRspBo.setRspStatus(ResponseEnum.SUCCESS);
        logger.info("请求响应：{}", saveAndHelloRspBo.toString());
        return saveAndHelloRspBo;
    }

    @Override
    public ListRspBo list(ListReqBo listReqBo) {
        ListRspBo rspBo = new ListRspBo();

        // 构建查询条件
        Specification<User> querySpecific = (Specification<User>) (root, query, cb) -> {
            List<Predicate> lstPredicates = new ArrayList<>();
            // 如果用户名 不为空
            if (StringUtils.isBlank(listReqBo.getName())) {
                lstPredicates.add(cb.like(root.get("name").as(String.class), "%" + listReqBo.getName() + "%"));
            }
            // 如果 密码 不为空
            if (StringUtils.isBlank(listReqBo.getPassword())) {
                lstPredicates.add(cb.equal(root.get("password").as(String.class), listReqBo.getPassword()));
            }
            Predicate[] arrayPredicates = new Predicate[lstPredicates.size()];
            return cb.and(lstPredicates.toArray(arrayPredicates));
        };

        // 查找符合条件的记录
        List<User> listProjectPo = this.userRepository.findAll(querySpecific);

        if (CollectionUtils.isEmpty(listProjectPo)) {
            rspBo.setRspStatus(ResponseEnum.SUCCESS);
            rspBo.setUsers(Collections.emptyList());
            return rspBo;
        }

        List<UserBo> users = new ArrayList<>();

        // 使用java8提供的forEach()方法和Lambda表达式
        listProjectPo.forEach(user -> {
            UserBo userBo = new UserBo();
            BeanUtils.copyProperties(user, userBo);
            users.add(userBo);
        });

        rspBo.setRspStatus(ResponseEnum.SUCCESS);
        rspBo.setUsers(users);
        return rspBo;
    }
}
