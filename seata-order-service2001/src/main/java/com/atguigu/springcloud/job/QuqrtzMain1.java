package com.atguigu.springcloud.job;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @Description TODO
 * @Author 张开源
 * @Date 2020/9/14 0:33
 * @Version 1.0
 **/
public class QuqrtzMain1 {
    public static void main(String[] args) throws SchedulerException {
        //1.创建Job对象，你要做什么
        JobDetail jobDetail= JobBuilder.newJob(QuqrtzDemo.class).build();

/*
        简单的trigger触发时间，通过Quartz提供一个方法来完成简单的重复调用
        cron Trigger 按照Cron的表达式来给定触发时间
*/
        //2创建trigger对象，在什么时候做
        Trigger trigger= TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * *?")).build();

        //3创建Scheduler对象，在什么时间做什么事
        Scheduler defaultScheduler = StdSchedulerFactory.getDefaultScheduler();
        defaultScheduler.scheduleJob(jobDetail,trigger);

        //启动
        defaultScheduler.start();
    }
}
