package com.zccoder.demo.excel.domain;

import java.io.Serializable;

import lombok.Data;

/**
 * 筛选条件
 *
 * @author zc
 * @date 2020/05/21
 */
@Data
public class SearchBean implements Serializable {

    /**
     * 模拟总数
     */
    private Integer total;

}
