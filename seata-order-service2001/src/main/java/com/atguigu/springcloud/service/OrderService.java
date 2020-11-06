package com.atguigu.springcloud.service;

import com.atguigu.springcloud.domain.Order;
import org.apache.ibatis.annotations.Param;

public interface OrderService {
    void createOrder(Order order);

    //根据订单Id查询
    Order selectById(@Param("id") Long id);
}
