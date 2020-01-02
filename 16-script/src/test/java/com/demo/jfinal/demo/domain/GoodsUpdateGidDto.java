package com.demo.jfinal.demo.domain;

import java.io.Serializable;

/**
 * 批量修改三方平台商品GID
 *
 * @author zc 2019-12-16
 */
public class GoodsUpdateGidDto implements Serializable {

    /**
     * 外部sku
     */
    private String outerSku;
    /**
     * 外部商品编码
     */
    private String outerWareId;
    /**
     * 商品GID
     */
    private Long goodsGid;

    @Override
    public String toString() {
        return "GoodsUpdateGidDto{" +
                "outerSku='" + outerSku + '\'' +
                ", outerWareId='" + outerWareId + '\'' +
                ", goodsGid=" + goodsGid +
                '}';
    }

    public String getOuterSku() {
        return outerSku;
    }

    public void setOuterSku(String outerSku) {
        this.outerSku = outerSku;
    }

    public String getOuterWareId() {
        return outerWareId;
    }

    public void setOuterWareId(String outerWareId) {
        this.outerWareId = outerWareId;
    }

    public Long getGoodsGid() {
        return goodsGid;
    }

    public void setGoodsGid(Long goodsGid) {
        this.goodsGid = goodsGid;
    }
}
