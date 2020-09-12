package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.AccountDao;
import com.atguigu.springcloud.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @Description TODO
 * @Author 张开源
 * @Date 2020/9/13 6:31
 * @Version 1.0
 **/
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountDao accountDao;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        log.info("-------->account-service中扣减账户余额开始");
        //模拟超时异常，处理全局事务
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        accountDao.decrease(userId,money);
        log.info("-------->account-service中扣减账户余额结束");
    }
}
