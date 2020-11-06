package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {
    //新建订单
    void createOrder(Order order);

    //修改订单状态
    void update(@Param("userId") Long userId,@Param("status") Integer status);

    //根据订单Id查询
    Order selectById(@Param("id") Long id);
}
