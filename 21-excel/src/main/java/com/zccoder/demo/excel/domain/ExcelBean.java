package com.zccoder.demo.excel.domain;

import com.alibaba.excel.annotation.ExcelProperty;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 模拟Excel导出
 *
 * @author zc
 * @date 2020/05/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelBean implements Serializable {

    /**
     * 编号
     */
    @ExcelProperty("编号")
    private Integer id;
    /**
     * 文本
     */
    @ExcelProperty("文本")
    private String text;

}
