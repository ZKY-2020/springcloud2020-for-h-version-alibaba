package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        log.info("id:"+id);
        CommonResult<Payment> paymentById = paymentFeignService.getPaymentById(id);
        log.info("查询结果："+paymentById);
        return paymentById;
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeOut(){
        //openfeign-ribbon客户端一般默认等待一秒
        return paymentFeignService.paymentFeignTimeOut();
    }
}
