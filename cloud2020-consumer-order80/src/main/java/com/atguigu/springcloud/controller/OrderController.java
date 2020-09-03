package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lib.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {
    /*public static  final String PAYMENT_URL="http://localhost:8001";*/
    public  static  final  String PAYMENT_URL="http://CLOUD-PAYMENT-SERVICE";
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private LoadBalancer loadBalanced;
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/consumer/payment/save")
    public CommonResult<Payment> save(@RequestBody Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/save",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        CommonResult commonResult = restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id,
                CommonResult.class);
        log.info("查询结果："+commonResult);
        return commonResult;
    }

    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id,
                CommonResult.class);
        log.info("查询结果："+entity);
        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else {
            return new CommonResult<Payment>(444,"操作失败",new Payment());
        }
    }

    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLB(){
        log.info("请求进入");
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("instance:"+instance.getInstanceId());
        }
        if (instances==null||instances.size()<=0){
            return null;
        }
        ServiceInstance serviceInstance = loadBalanced.instances(instances);
        log.info("serviceInstance.serviceId:"+serviceInstance.getServiceId());
        URI uri = serviceInstance.getUri();
        log.info("uri:"+uri);
        //注销掉自定义轮询算法，需要注销掉LoadBalancer里面的loadBanLance注解，并且此处为URI，若加上注解，此处需要用PAYMENT_URL
        String template = restTemplate.getForObject(PAYMENT_URL + "/payment/lb", String.class);
        log.info("restTemplate.getForObject(uri+\"/payment/lb\",String.class):"+template);
        return template;
    }
}
