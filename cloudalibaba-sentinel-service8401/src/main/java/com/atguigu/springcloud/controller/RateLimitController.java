package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.myHandler.CustomerBlockHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author 张开源
 * @Date 2020/9/11 23:16
 * @Version 1.0
 **/
@RestController
@Slf4j
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommonResult byResource(){
        log.info("byResource");
        return new CommonResult(200,"按照资源名称限流测试ok","serial001");
    }

    public CommonResult handleException(BlockException exception){
        log.info("handleException");
        return new CommonResult(444,exception.getClass().getCanonicalName()+"\t 服务不可用",null);
    }

    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byResource")
    public CommonResult byUrl(){
        log.info("byUrl");
        return new CommonResult(200,"按照url限流测试ok",new Payment(2020L,"serial002"));
    }


    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handleException2")
    public CommonResult customerBlockHandler(){
        log.info("customerBlockHandler");
        return new CommonResult(200,"按照客户自定义测试ok",new Payment(2020L,"serial003"));
    }
}
