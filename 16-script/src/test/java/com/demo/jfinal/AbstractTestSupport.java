package com.demo.jfinal;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;

import org.junit.After;
import org.junit.BeforeClass;

/**
 * 单元测试基类
 *
 * @author zc 2020-01-02
 */
public abstract class AbstractTestSupport {

    private static DruidPlugin dp;
    private static ActiveRecordPlugin activeRecord;

    /**
     * 数据连接地址
     */
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/epet_goods_basic?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&tinyInt1isBit=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_TYPE = "mysql";

    @BeforeClass
    public static void setUpBeforeClass() {
        dp = new DruidPlugin(URL, USERNAME, PASSWORD, DRIVER);
        dp.addFilter(new StatFilter());
        dp.setInitialSize(3);
        dp.setMinIdle(2);
        dp.setMaxActive(5);
        dp.setMaxWait(60000);
        dp.setTimeBetweenEvictionRunsMillis(120000);
        dp.setMinEvictableIdleTimeMillis(120000);

        WallFilter wall = new WallFilter();
        wall.setDbType(DATABASE_TYPE);
        dp.addFilter(wall);

        dp.getDataSource();
        dp.start();

        activeRecord = new ActiveRecordPlugin(dp);
        activeRecord.setDialect(new MysqlDialect())
                .setDevMode(true)
                .setShowSql(true);

        activeRecord.start();
    }

    @After
    public void tearDown() {
        activeRecord.stop();
        dp.stop();
    }

}
