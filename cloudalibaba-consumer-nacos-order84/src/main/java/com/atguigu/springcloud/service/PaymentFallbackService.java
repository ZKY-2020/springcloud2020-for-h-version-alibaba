package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author 张开源
 * @Date 2020/9/12 6:44
 * @Version 1.0
 **/
@Component
public class PaymentFallbackService implements PaymentService{
    @Override
    public CommonResult<Payment> paymentSql(Long id) {
        return new CommonResult<Payment>(444,"服务降级返回,----PaymentFallbackService",new Payment(id,"errorSerial"));
    }
}
