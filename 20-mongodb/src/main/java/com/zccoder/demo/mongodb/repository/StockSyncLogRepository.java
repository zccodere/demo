package com.zccoder.demo.mongodb.repository;

import com.zccoder.demo.mongodb.domain.StockSyncLog;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

/**
 * 库存同步日志数据层
 *
 * @author zc
 * @date 2020/05/08
 */
public interface StockSyncLogRepository extends MongoRepository<StockSyncLog, BigInteger> {


}
