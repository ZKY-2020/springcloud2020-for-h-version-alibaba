package com.atguigu.springcloud.config;

import feign.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class FeignConfig {
    @Bean
    Logger.Level feignLoggerLevel(){
        //NONE 默认的，不显示任何日志，BASIC 仅记录请求方法，URL,响应状态码，以及响应时间，FULL 除了HEADERS中定义的信息外，还有请求和
        //响应的正文以及元数据
        return Logger.Level.FULL;
    }
}
