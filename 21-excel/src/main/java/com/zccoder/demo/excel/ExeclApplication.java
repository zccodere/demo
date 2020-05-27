package com.zccoder.demo.excel;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.zccoder.demo.excel.domain.DealStatus;
import com.zccoder.demo.excel.domain.DemoResponse;
import com.zccoder.demo.excel.domain.ExcelBean;
import com.zccoder.demo.excel.domain.PageBean;
import com.zccoder.demo.excel.domain.SearchBean;
import com.zccoder.demo.excel.service.DemoService;

import java.io.File;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 启动类
 *
 * @author zc
 * @date 2020/05/21
 */
public class ExeclApplication {

    public static void main(String[] args) {
        DealStatus dealStatus = new DealStatus();
        dealStatus.setMessage("执行成功");
        ThreadPoolExecutor executor = null;

        try {
            executor = doCreateExcel(dealStatus);
        } catch (Exception e) {
            // 出现未知异常
            dealStatus.setMessage(Thread.currentThread().getName() + "：导出任务执行异常，详细信息：" + e.getMessage());
            e.printStackTrace();
        } finally {
            // 线程池一定要关闭
            if (Objects.nonNull(executor)) {
                executor.shutdownNow();
            }
        }

        // 打印执行结果
        System.out.println(dealStatus);
    }

    private static ThreadPoolExecutor doCreateExcel(DealStatus dealStatus) {
        SearchBean searchBean = new SearchBean();
        searchBean.setTotal(7500);

        String fileName = "测试数据导出" + ExcelTypeEnum.XLSX.getValue();

        DemoResponse response = DemoService.search(searchBean, new PageBean());
        PageBean pageBean = response.getPageBean();

        // 安全保护，防止OOM
        int total = pageBean.getTotal();
        // 假设单条数据占用1024字节
        int singleDataByte = 1024;
        int oneMbByte = 1048576;
        int maxMb = 100;
        if (total * singleDataByte > maxMb * oneMbByte) {
            dealStatus.setMessage("系统已拒绝执行导出任务，因为导出数据所需内存已超过系统阈值：" + maxMb + "MB。请缩小导出数据范围！");
            return null;
        }

        int totalPages = pageBean.getTotalPages();
        int curPage = 1;

        int corePoolSize = 10;
        int workSize = totalPages - corePoolSize;
        workSize = workSize > 1 ? workSize : 1;

        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(workSize);
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat(fileName + "导出任务线程池-%d")
                .build();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 0, TimeUnit.SECONDS, workQueue, threadFactory);

        // 如果线程池任务执行异常，则结束线程池
        CountDownLatch countDownLatch = new CountDownLatch(1);
        // 只允许串行写入Excel
        Semaphore semaphore = new Semaphore(1);

        // 这里 需要指定写用哪个class去写
        File tempFile = new File(fileName);
        System.out.println("文件路径：" + tempFile.getAbsolutePath());

        ExcelWriter excelWriter = EasyExcel.write(tempFile, ExcelBean.class).build();
        // 这里注意 如果同一个sheet只要创建一次
        WriteSheet writeSheet = EasyExcel.writerSheet("sheet0").build();
        excelWriter.write(response.getDataList(), writeSheet);

        // 根据数据库分页的总的页数来
        if (totalPages > curPage) {
            for (int i = curPage + 1; i <= totalPages; i++) {
                final int curPageTemp = i;
                executor.submit(() -> {
                    if (countDownLatch.getCount() < 1) {
                        return;
                    }

                    try {
                        pageBean.setCurrentPage(curPageTemp);
                        DemoResponse responseTemp = DemoService.search(searchBean, pageBean);

                        semaphore.acquire();
                        int b = new Random().nextInt(1000);
                        if (b < 100) {
                            throw new RuntimeException("手动随机异常");
                        }
                        // 只有任务执行无异常时，才写入Excel
                        if (countDownLatch.getCount() > 0) {
                            excelWriter.write(responseTemp.getDataList(), writeSheet);
                        }
                        semaphore.release();
                    } catch (Exception e) {
                        countDownLatch.countDown();
                        dealStatus.setMessage(Thread.currentThread().getName() + "：导出任务执行异常，详细信息：" + e.getMessage());
                        if (!(e instanceof InterruptedException)) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }

        while (true) {
            if (countDownLatch.getCount() < 1 || executor.getActiveCount() < 1) {
                break;
            }
        }

        // 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();

        return executor;
    }
}
