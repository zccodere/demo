package com.zccoder.demo.excel.domain;

import java.io.Serializable;

import lombok.Data;

/**
 * 处理状态
 *
 * @author zc
 * @date 2020/05/21
 */
@Data
public class DealStatus implements Serializable {

    /**
     * 处理结果
     */
    private String message;

}
