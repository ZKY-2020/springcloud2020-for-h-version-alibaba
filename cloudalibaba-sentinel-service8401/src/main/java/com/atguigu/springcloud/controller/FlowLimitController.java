package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Description TODO
 * @Author 张开源
 * @Date 2020/9/10 23:22
 * @Version 1.0
 **/
@RestController
@Slf4j
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA(){
        log.info("---------this is testA");
        try {
            TimeUnit.MILLISECONDS.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "---------this is testA";
    }

    @GetMapping("/testB")
    public String testB(){
        log.info(Thread.currentThread().getName()+"\t"+"...testB");
        return "---------this is testB";
    }

    @GetMapping("/testD")
    public String testD(){
        log.info(Thread.currentThread().getName()+"\t"+"...testD 测试RT");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "---------this is testD";
    }

    @GetMapping("/testF")
    public String testF(){
        log.info("...testD 异常比例");
        int age=10/0;
        return "---------this is testF,异常比例";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2){
        log.info("进入testHotKey");
        return "---testHotKey";
    }

    public String deal_testHotKey(String s1, String s2, BlockException exception){
        log.info("进入deal_testHotKey，规则内");
        return "------------deal_testHotKey";//sentinel系统默认的提示，Blocked by Sentinel(flow limiting)
    }
}
