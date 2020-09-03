package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    //正常返回
    public String paymentInfo_OK(Integer id){
        return "线程池："+Thread.currentThread().getName()+"\t"+"paymentInfo_OK,id: "+id+"\t"+"成功返回！！";
    }

    //返回超时3秒
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")})
    public String paymentInfo_TimeOut(Integer id){
        int timeNumber=1;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+"\t"+"paymentInfo_TimeOut,id: "+id+"\t"+"返回未超时！！"+" 耗时:"+timeNumber+"秒钟";
    }

    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池："+Thread.currentThread().getName()+"\t"+"系统繁忙，请稍后再试,id: "+id+"\t"+"返回超时！！";
    }

    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id<0){
            throw new RuntimeException("*******id  不能为负数");
        }
        String simpleUUID = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号："+simpleUUID;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        String s="id 不能为负数，请稍后重试不为负数的id!------ id:"+id;
        return s;
    }
}
