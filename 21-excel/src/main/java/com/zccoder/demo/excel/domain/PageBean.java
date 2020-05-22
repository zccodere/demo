package com.zccoder.demo.excel.domain;

import java.io.Serializable;

import lombok.Data;

/**
 * 分页参数
 *
 * @author zc
 * @date 2020/05/21
 */
@Data
public class PageBean implements Serializable {

    /**
     * 当前页
     */
    private int currentPage;
    /**
     * 每页大小
     */
    private int pageSize;
    /**
     * 总页数
     */
    private int totalPages;
    /**
     * 总记录数
     */
    private int total;

    public PageBean() {
        this.pageSize = 20;
        this.currentPage = 1;
        this.totalPages = 0;
        this.total = 0;
    }

    public void calculate(int total) {
        this.setTotal(total);
        this.totalPages = (total / pageSize) + ((total % pageSize) > 0 ? 1 : 0);
        if (currentPage > totalPages) {
            throw new IllegalStateException("超出查询范围");
        }
    }
}
