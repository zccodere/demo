package com.zccoder.demo.cloud.service.order;

import com.zccoder.demo.cloud.service.order.bo.OrderCreateReqBO;
import com.zccoder.demo.cloud.service.order.bo.OrderInfo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 订单服务
 *
 * @author zc 2018-10-12
 **/
@RequestMapping("/order")
public interface OrderService {

    /**
     * 创建订单
     *
     * @param reqBO 入参BO
     * @return 订单ID
     */
    @PostMapping("/create")
    String create(@RequestBody OrderCreateReqBO reqBO);

    /**
     * 通过用户ID查询订单
     *
     * @param userId 用户ID
     * @return 订单集合
     */
    @PostMapping("/list")
    List<OrderInfo> list(@RequestParam("userId") String userId);
}
