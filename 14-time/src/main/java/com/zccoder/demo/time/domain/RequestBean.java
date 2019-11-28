package com.zccoder.demo.time.domain;

import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 请求对象
 *
 * @author zc 2019-08-30
 */
public class RequestBean implements Serializable {

    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDateTime startTime;

    @Override
    public String toString() {
        return "RequestBean{" +
                "startDate=" + startDate +
                ", startTime=" + startTime +
                '}';
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
}
