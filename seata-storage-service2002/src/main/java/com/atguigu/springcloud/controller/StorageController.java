package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.domain.CommonResult;
import com.atguigu.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author 张开源
 * @Date 2020/9/13 6:01
 * @Version 1.0
 **/
@RestController
@Slf4j
public class StorageController {
    @Resource
    private StorageService storageService;

    /*扣减库存*/
    @GetMapping("/storage/decrease")
    public CommonResult decrease(Long productId, Integer count){
        storageService.decrease(productId,count);
        return new CommonResult(200,"扣减库存成功",null);
    }
}
