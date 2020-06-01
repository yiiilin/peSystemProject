package com.fzn.pesystem.quartz.quartz;

import com.fzn.pesystem.quartz.service.QuartzService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PerformanceCalculationQuartz implements Job {
    Logger logger = LoggerFactory.getLogger(PerformanceCalculationQuartz.class);
    @Autowired
    private QuartzService service;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        service.autoUpdate(new Date());
        logger.info("绩效统计：" + String.valueOf(new Date()));
    }
}
