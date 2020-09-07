package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description api层 nacos动态刷新功能
 * @Author 张开源
 * @Date 2020/9/8 0:43
 * @Version 1.0
 **/
@RestController
@RefreshScope //支持nacos动态刷新功能
@Slf4j
public class ConfigClientController {
    @Value("${config.info}")
    private String configInfo;


    @GetMapping("/config/info")
    public String getConfigInfo(){
        log.info("configInfo:"+configInfo);
        return configInfo;
    }
}
