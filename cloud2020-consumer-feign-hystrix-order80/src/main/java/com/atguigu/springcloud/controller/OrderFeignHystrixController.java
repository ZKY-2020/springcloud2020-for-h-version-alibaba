package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentFeignHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderFeignHystrixController {
    @Resource
    private PaymentFeignHystrixService paymentFeignHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String infoOk = paymentFeignHystrixService.paymentInfo_OK(id);
        log.info("****result:"+infoOk);
        return infoOk;
    }


    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentTimeoutFallbackMethod",commandProperties =
            {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")})
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        log.info("id:"+id);
        String infoTimeOut = paymentFeignHystrixService.paymentInfo_TimeOut(id);
        log.info("超时："+infoTimeOut);
        return infoTimeOut;
    }

    public String paymentTimeoutFallbackMethod(Integer id){
        return "消费者80，对方支付系统繁忙，请10秒钟后再试试！";
    }

    //全局fallBack方法
    public String payment_Global_FallbackMethod(){
        return "Global 异常处理信息，请稍后再试";
    }

    //zipkin+sleuth
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin(){
        String paymentZipkin = paymentFeignHystrixService.paymentZipkin();
        log.info("paymentZipkin:"+paymentZipkin);
        return paymentZipkin;
    }
}
