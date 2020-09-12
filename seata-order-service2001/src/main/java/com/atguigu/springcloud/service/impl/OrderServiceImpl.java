package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.OrderDao;
import com.atguigu.springcloud.domain.Order;
import com.atguigu.springcloud.service.AccountService;
import com.atguigu.springcloud.service.OrderService;
import com.atguigu.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author 张开源
 * @Date 2020/9/13 2:38
 * @Version 1.0
 **/
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;

    @Override
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public void createOrder(Order order) {
        log.info("----->开始新建订单");
        orderDao.createOrder(order);
        log.info("----->订单服务开始调用库存，做扣减count开始");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("----->订单服务开始调用库存,做扣减count结束");
        log.info("----->订单微服务调用账户，做扣减money开始");
        accountService.decrease(order.getUserId(),order.getMoney());
        //修改订单状态，从0到1,1代表完成
        log.info("------>修改订单状态开始");
        orderDao.update(order.getUserId(),0);
        log.info("------>修改订单状态结束");
        log.info("------>下订单结束");
    }
}
