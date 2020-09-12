package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author 张开源
 * @Date 2020/9/12 4:28
 * @Version 1.0
 **/
@RestController
@Slf4j
public class CircleBreakerController {
    public  static  final  String SERVICE_URL="http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback")//未自定义熔断
   // @SentinelResource(value = "fallback",fallback = "handlerFallback") //fallback只负责业务异常
   // @SentinelResource(value = "fallback",blockHandler = "blockHandler")//blockHandler只负责sentinel控制台配置违规
    @SentinelResource(value = "fallback",fallback = "handlerFallback",blockHandler = "blockHandler",
            exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult<Payment> fallback(@PathVariable("id") Long id){
        CommonResult commonResult = restTemplate.getForObject(SERVICE_URL + "/paymentSql/" + id, CommonResult.class, id);
        log.info("commonResult:"+commonResult);
        if(id==4){
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常。。。");
        }else if(commonResult.getData()==null){
            throw  new NullPointerException("NullPointerException,该id没有对应的记录，空指针异常。。。");
        }
        return commonResult;
    }

    //fallback例子
    public CommonResult handlerFallback(@PathVariable("id") Long id,Throwable throwable){
        Payment payment = new Payment(id, null);
        log.info("fallback方法,id:"+id);
        return new CommonResult(444,"兜底异常handlerFallback，exception内容："+throwable.getMessage(),payment);
    }

    //blockHandler例子
    public CommonResult blockHandler(@PathVariable("id") Long id, BlockException blockException){
        Payment payment = new Payment(id, null);
        log.info("blockHandler方法,id:"+id);
        return new CommonResult(445,"blockHandler-sentinel限流，无此流水，blockException内容："+blockException.getMessage(),payment);
    }

    //----------openfeign
    @GetMapping(value = "/consumer/paymentSql/{id}")
    public CommonResult<Payment> paymentSql(@PathVariable("id") Long id){
        CommonResult<Payment> commonResult = paymentService.paymentSql(id);
        log.info("commonResult:"+commonResult);
        return commonResult;
    };
}
