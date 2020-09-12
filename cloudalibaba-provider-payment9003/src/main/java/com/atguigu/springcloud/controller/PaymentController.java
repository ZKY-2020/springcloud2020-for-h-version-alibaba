package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * @Description TODO
 * @Author 张开源
 * @Date 2020/9/12 3:59
 * @Version 1.0
 **/
@RestController
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> hashMap=new HashMap<>();

    static {
        hashMap.put(1L,new Payment(1L,"FASDF23323FSDFEF3"));
        hashMap.put(2L,new Payment(2L,"FSD22222222222222"));
        hashMap.put(2L,new Payment(3L,"675JGHJHG43534353"));
        hashMap.put(4L,new Payment(4L,"VD3234444444444424"));
        hashMap.put(5L,new Payment(5L,"HHF444444444444444"));
    }

    @GetMapping(value = "/paymentSql/{id}")
    public CommonResult<Payment> paymentSql(@PathVariable("id") Long id){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = format.format(new Date());
        log.info("id:"+id+"\t"+s);
        Payment payment = hashMap.get(id);
        log.info("payment:"+payment);
        CommonResult<Payment> commonResult = new CommonResult<>(200, "from mysql,serverPort:" + serverPort, payment);
        return commonResult;
    }
}

