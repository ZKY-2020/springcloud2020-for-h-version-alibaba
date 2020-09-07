package com.atguigu.springcloud.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description TODO
 * @Author 张开源
 * @Date 2020/9/6 4:27
 * @Version 1.0
 **/
@Component
@EnableScheduling
@Slf4j
public class JobTest {
    @Scheduled(cron = "*/100 * * * * ?")
    public void execute(){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = format.format(new Date());
        log.info("定时执行开始："+s);
        String ss = format.format(new Date());
        log.info("定时执行结束："+ss);
    }
}
