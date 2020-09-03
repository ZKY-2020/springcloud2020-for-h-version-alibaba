package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author 张开源
 * @Date 2020/8/22 22:48
 * @Version 1.0
 **/
@Component
public class PaymentFallbackService implements PaymentFeignHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        String s="PaymentFallbackService fall back paymentInfo_OK";
        return s;
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        String s="PaymentFallbackService fall back paymentInfo_TimeOut";
        return s;
    }
}
