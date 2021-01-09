package com.yuansb.demo.learn.codeScheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 通过 @Scheduled 声明该方法是计划任务，使用 fixedRete 属性每隔国定时间执行。
 *
 * 使用 cron 属性可按照指定时间执行，cron 是 UNIX 和类 UNIX (Linux) 系统下的定时任务。
 */
@Service
public class ScheduledTaskService {

    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        System.out.println("每隔五秒执行一次 " + dateFormat.format(new Date()));
    }

    /**
     * 每天 15 点 56 分执行
     */
    @Scheduled(cron = "0 56 15 ? * *")
    public void fixTimeExecution() {
        System.out.println("在指定时间 " + dateFormat.format(new Date()) + " 执行");
    }

}
