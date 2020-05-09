package com.zccoder.demo.mongodb.repository;

import com.zccoder.demo.mongodb.MongodbApplicationTest;
import com.zccoder.demo.mongodb.domain.StockSyncLog;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;

/**
 * 库存同步日志数据层测试类
 *
 * @author zc
 * @date 2020/05/08
 */
public class StockSyncLogRepositoryTest extends MongodbApplicationTest {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private StockSyncLogRepository stockSyncLogRepository;

    @Test
    public void save() {
        StockSyncLog log = new StockSyncLog();
        log.setGoodsId(10);
        log.setStockNum(3);
        log.setSyncCode(0);
        log.setSyncMessage("成功");
        log.setCreatetime(LocalDateTime.now());
        StockSyncLog save = this.stockSyncLogRepository.insert(log);
        System.out.println("success：" + save);
    }

    @Test
    public void findOne() {
        BigInteger id = new BigInteger("29310460028817657652153740560");
        Optional<StockSyncLog> log = this.stockSyncLogRepository.findById(id);
        log.ifPresent(System.out::println);
    }

    @Test
    public void last() {
        StockSyncLog log = new StockSyncLog();
        log.setGoodsId(11);
        Example<StockSyncLog> example = Example.of(log);

        Sort sort = Sort.by(Sort.Direction.DESC, "createtime");

        Page<StockSyncLog> page = this.stockSyncLogRepository.findAll(example, PageRequest.of(0, 2, sort));

        List<StockSyncLog> content = page.getContent();
        content.forEach(System.out::println);
    }

    @Test
    public void selectIn() {
        // https://docs.spring.io/spring-data/mongodb/docs/2.2.7.RELEASE/reference/html/#mongodb-template-query
        Query query = Query.query(Criteria.where("goodsId").in(10, 11));
        // 类似SQL：select * from table where goodsId in (10,11)
        List<StockSyncLog> logList = mongoTemplate.find(query, StockSyncLog.class);
        logList.forEach(System.out::println);
    }

    @Test
    public void groupBy() {

        Criteria goodsId = Criteria.where("goodsId").in(10, 11, 12);
        Criteria syncCode = Criteria.where("syncCode").is(1);

        Aggregation aggregation = Aggregation.newAggregation(new MatchOperation(goodsId), new MatchOperation(syncCode), group("goodsId").last("stockNum").as("stockNum").last("goodsId").as("goodsId"));

        AggregationResults<StockSyncLog> stockSyncLog = mongoTemplate.aggregate(aggregation, "stockSyncLog", StockSyncLog.class);
        List<StockSyncLog> logList = stockSyncLog.getMappedResults();
        logList.forEach(System.out::println);
    }
}
