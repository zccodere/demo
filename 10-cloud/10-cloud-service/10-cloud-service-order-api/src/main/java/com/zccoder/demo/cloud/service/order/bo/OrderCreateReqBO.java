package com.zccoder.demo.cloud.service.order.bo;

import java.io.Serializable;

/**
 * 创建订单入参BO
 *
 * @author zc 2018-10-12
 **/
public class OrderCreateReqBO implements Serializable {

    private static final long serialVersionUID = 1622429617933974559L;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 商品ID
     */
    private String goodsId;
    /**
     * 商品名称
     */
    private String goodsName;

    @Override
    public String toString() {
        return "OrderCreateReqBO{" +
                "userId='" + userId + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                '}';
    }

    /**
     * 获取 用户ID
     *
     * @return userId 用户ID
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * 设置 用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取 商品ID
     *
     * @return goodsId 商品ID
     */
    public String getGoodsId() {
        return this.goodsId;
    }

    /**
     * 设置 商品ID
     *
     * @param goodsId 商品ID
     */
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取 商品名称
     *
     * @return goodsName 商品名称
     */
    public String getGoodsName() {
        return this.goodsName;
    }

    /**
     * 设置 商品名称
     *
     * @param goodsName 商品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}
