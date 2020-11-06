package com.atguigu.springcloud.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description TODO
 * @Author 张开源
 * @Date 2020/9/14 0:13
 * @Version 1.0
 **/
@Slf4j
public class QuqrtzDemo implements Job {
    /*任务被触发时所执行的方法*/
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = format.format(new Date());
        log.info("------->execute定时任务开始:"+s);
    }
}
