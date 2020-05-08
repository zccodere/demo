package com.zccoder.demo.mongodb.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * 库存同步日志
 *
 * @author zc
 * @date 2020/05/08
 */
@Document
@Data
public class StockSyncLog {

    /**
     * 主键
     */
    @Id
    private BigInteger id;
    /**
     * 商品编号
     */
    private Integer goodsId;
    /**
     * 库存数量
     */
    private Integer stockNum;
    /**
     * 同步状态（0失败 1成功）
     */
    private Integer syncCode;
    /**
     * 同步结果
     */
    private String syncMessage;
    /**
     * 创建时间
     */
    private LocalDateTime createtime;
}
