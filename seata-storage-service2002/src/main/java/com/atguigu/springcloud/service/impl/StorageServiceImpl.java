package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.StorageDao;
import com.atguigu.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author 张开源
 * @Date 2020/9/13 5:58
 * @Version 1.0
 **/
@Service
@Slf4j
public class StorageServiceImpl implements StorageService {
    @Resource
    private StorageDao storageDao;
    @Override
    public void decrease(Long productId, Integer count) {
        log.info("------->storage-service中扣减库存开始");
        storageDao.decrease(productId,count);
        log.info("------->storage-service中扣减库存结束");
    }
}
