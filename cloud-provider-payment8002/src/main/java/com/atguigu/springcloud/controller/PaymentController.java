package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/save")
    public CommonResult save(@RequestBody Payment payment){
        int result = paymentService.save(payment);
        log.info("插入数据："+result);
        if (result>0){
            return new CommonResult(200,"插入数据成功,serverPort:"+serverPort,result);
        }else {
            return new CommonResult(444,"插入数据失败",result);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment paymentById = paymentService.getPaymentById(id);
        log.info("查询结果："+paymentById);
        if (paymentById!=null){
            return new CommonResult(200,"查询成功,serverPort:"+serverPort ,paymentById);
        }else {
            return new CommonResult(444,"没有对应记录，查询Id:"+id,null);
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> regions = discoveryClient.getServices();
        for (String region : regions) {
            log.info("****element:"+region);
        }

        List<ServiceInstance> instancesById = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instanceInfo : instancesById) {
            log.info(instanceInfo.getServiceId()+"\t"+instanceInfo.getHost()+"\t"+instanceInfo.getPort()+"\t"+instanceInfo.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeOut(){
        try {
            //暂停三秒
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("serverPort:"+serverPort);
        return serverPort;
    }
}
