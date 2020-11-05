package com.yuansb.demo.task.quartz.job;

import cn.hutool.core.date.DateUtil;
import com.yuansb.demo.task.quartz.job.base.BaseJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;

/**
 * Test Job
 */
@Slf4j
public class TestJob implements BaseJob {

    @Override
    public void execute(JobExecutionContext context) {
        log.error("Test Job 执行时间: {}", DateUtil.now());
    }
}