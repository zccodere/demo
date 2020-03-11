package com.zccoder.demo.cloud.service.order.web;

import com.zccoder.demo.cloud.service.order.OrderService;
import com.zccoder.demo.cloud.service.order.bo.OrderCreateReqBO;
import com.zccoder.demo.cloud.service.order.bo.OrderInfo;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单服务实现
 *
 * @author zc 2018-10-12
 **/
@RestController
public class OrderServiceImpl implements OrderService {

    private Map<String, List<OrderInfo>> store = new HashMap<>(8);

    @Override
    public String create(OrderCreateReqBO reqBO) {
        List<OrderInfo> orders = new ArrayList<>(8);
        if (store.containsKey(reqBO.getUserId())) {
            orders = store.get(reqBO.getUserId());
        }

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(reqBO.getUserId());
        orderInfo.setGoodsId(reqBO.getGoodsId());
        orderInfo.setGoodsName(reqBO.getGoodsName());
        String orderId = System.currentTimeMillis() + "";
        orderInfo.setOrderId(orderId);
        orders.add(orderInfo);

        store.put(reqBO.getUserId(), orders);

        return orderId;
    }

    @Override
    public List<OrderInfo> list(String userId) {
        return store.get(userId);
    }

}
