package com.zccoder.demo.excel.service;

import com.zccoder.demo.excel.domain.DemoResponse;
import com.zccoder.demo.excel.domain.ExcelBean;
import com.zccoder.demo.excel.domain.PageBean;
import com.zccoder.demo.excel.domain.SearchBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 示例服务
 *
 * @author zc
 * @date 2020/05/21
 */
public class DemoService {

    public static DemoResponse search(SearchBean searchBean, PageBean pageBean) {
        pageBean.calculate(searchBean.getTotal());

        int start = pageBean.getPageSize() * (pageBean.getCurrentPage() - 1);
        int end = start + pageBean.getPageSize();

        if (end > searchBean.getTotal()){
            end = searchBean.getTotal();
        }

        List<ExcelBean> dataList = new ArrayList<>(pageBean.getPageSize());
        for (int i = start; i < end; i++) {
            dataList.add(new ExcelBean(i, "文本" + i + "完"));
        }

        DemoResponse demoResponse = new DemoResponse();
        demoResponse.setDataList(dataList);
        demoResponse.setPageBean(pageBean);
        return demoResponse;
    }
}
