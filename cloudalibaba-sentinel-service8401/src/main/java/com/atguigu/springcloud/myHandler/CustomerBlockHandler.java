package com.atguigu.springcloud.myHandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description TODO
 * @Author 张开源
 * @Date 2020/9/11 23:39
 * @Version 1.0
 **/
@Slf4j
public class CustomerBlockHandler {
    public static CommonResult handleException(BlockException exception){
        log.info("按照客户自定义,global handleException-----1");
        return new CommonResult(200,"按照客户自定义,global handleException------1",new Payment(2020L,"serial003"));
    }

    public static CommonResult handleException2(BlockException exception){
        log.info("按照客户自定义,global handleException-----2");
        return new CommonResult(200,"按照客户自定义,global handleException------2",new Payment(2020L,"serial003"));
    }
}
