package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.domain.CommonResult;
import com.atguigu.springcloud.domain.Order;
import com.atguigu.springcloud.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author 张开源
 * @Date 2020/9/13 3:56
 * @Version 1.0
 **/
@RestController
@Slf4j
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/order/create")
    public CommonResult create(Order order){
        log.info("order:"+order);
        orderService.createOrder(order);
        return new CommonResult(200,"创建订单成功",order);
    }

    @GetMapping("/order/select/{id}")
    public CommonResult selectById(@PathVariable("id") Long id){
        Order selectById = orderService.selectById(id);
        return new CommonResult(200,"查询成功",selectById);
    }
}
