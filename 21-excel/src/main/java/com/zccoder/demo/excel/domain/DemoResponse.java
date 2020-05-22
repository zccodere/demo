package com.zccoder.demo.excel.domain;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 响应参数
 *
 * @author zc
 * @date 2020/05/21
 */
@Data
public class DemoResponse implements Serializable {

    /**
     * 模拟数据列表
     */
    private List<ExcelBean> dataList;
    /**
     * 分页参数
     */
    private PageBean pageBean;

}
