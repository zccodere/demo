package com.zccoder.demo.time.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 响应对象
 *
 * @author zc 2019-08-30
 */
public class ResponseBean implements Serializable {

    private LocalDate resultDate;
    private LocalDateTime resultTime;

    @Override
    public String toString() {
        return "ResponseBean{" +
                "resultDate=" + resultDate +
                ", resultTime=" + resultTime +
                '}';
    }

    public LocalDate getResultDate() {
        return resultDate;
    }

    public void setResultDate(LocalDate resultDate) {
        this.resultDate = resultDate;
    }

    public LocalDateTime getResultTime() {
        return resultTime;
    }

    public void setResultTime(LocalDateTime resultTime) {
        this.resultTime = resultTime;
    }
}
