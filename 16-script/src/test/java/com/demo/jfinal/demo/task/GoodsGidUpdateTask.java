package com.demo.jfinal.demo.task;

import com.alibaba.fastjson.JSONObject;
import com.demo.jfinal.AbstractTestSupport;
import com.demo.jfinal.demo.domain.GoodsUpdateGidDto;
import com.demo.jfinal.util.DateUtils;
import com.demo.jfinal.util.HttpUtils;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 三方平台商品GID修改
 *
 * @author zc 2019-12-16
 */
public class GoodsGidUpdateTask extends AbstractTestSupport {

    private static final String auth = "auth";
    private static final boolean isProd = false;

    @Test
    public void run() {
        System.out.println("start convert ...");
        long startTime = System.currentTimeMillis();
        doRun();
        long continueTime = System.currentTimeMillis() - startTime;
        System.out.println("end convert ...");
        System.out.println("共耗时：" + continueTime / 1000 + " 秒");
    }

    private void doRun() {
        long startTime = System.currentTimeMillis();
        String sqlOne = "select outer_ware_id,outer_sku,goods_gid from a_jd_gid_task where mark_code = 1";

        List<GoodsUpdateGidDto> list = this.getList(sqlOne);
        Map<String, List<GoodsUpdateGidDto>> wareMap = list.stream().collect(Collectors.groupingBy(GoodsUpdateGidDto::getOuterWareId));

        long num = 0;
        long count = wareMap.size();
        for (Map.Entry<String, List<GoodsUpdateGidDto>> entry : wareMap.entrySet()) {
            ++num;

            doSync(entry.getKey(), entry.getValue());

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // 忽略
            }
            if (num % 100 == 0) {
                long continueTime = System.currentTimeMillis() - startTime;
                System.out.println("》》》》》》已耗时：" + continueTime / 1000 + " 秒");
                System.out.println("》》》》》》还剩余：" + DateUtils.getTimeFormat(DateUtils.getSeconds(count - num, 120, 60)));
            }
        }
    }

    private List<GoodsUpdateGidDto> getList(String sqlOne) {
        List<Record> gidList = Db.find(sqlOne);
        List<GoodsUpdateGidDto> dtoList = new ArrayList<>(gidList.size());
        gidList.forEach(gid -> {
            GoodsUpdateGidDto dto = new GoodsUpdateGidDto();
            dto.setOuterWareId(gid.getStr("outer_ware_id"));
            dto.setOuterSku(gid.getStr("outer_sku"));
            dto.setGoodsGid(gid.getBigInteger("goods_gid").longValue());
            dtoList.add(dto);
        });
        return dtoList;
    }

    private void doSync(String outerWareId, List<GoodsUpdateGidDto> list) {
        String url;
        // 测试
        url = "http://dev";
        if (isProd) {
            // 生产
            url = "http://prod";
        }

        String response = HttpUtils.doPost(auth, url, list);
        JSONObject jsonObject = JSONObject.parseObject(response);

        if (jsonObject.getString("status_code").equals("200")) {
            this.updateTaskMark(outerWareId, 1, response);
            System.out.println(outerWareId + " ======》 " + response);
        } else if (jsonObject.getString("status_code").equals("401")) {
            throw new RuntimeException("登录失效：" + jsonObject);
        } else {
            // 接口返回失败
            this.updateTaskMark(outerWareId, 2, response);
            System.err.println("执行失败：" + jsonObject);
        }
    }

    private void updateTaskMark(String outerWareId, int markCode, String markMsg) {
        String sql = "update a_jd_gid_task set mark_code = " + markCode + ",mark_msg = '" + markMsg + "' where outer_ware_id=" + outerWareId;
        Db.update(sql);
    }
}
