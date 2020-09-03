package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderZkController {
    public  static  final  String INVOKE_URL="http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;
    @Value("${server.port}")
    private String serverPort;
    @GetMapping(value = "/consumer/payment/zk")
    public String paymentInfo(){
        log.info("获取端口："+serverPort);
        String result = restTemplate.getForObject(INVOKE_URL + "/payment/zk", String.class);
        log.info("调用支付获取结果："+result);
        return result;
    }
}
