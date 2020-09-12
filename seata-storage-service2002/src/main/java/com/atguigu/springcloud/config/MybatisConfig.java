package com.atguigu.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description TODO
 * @Author 张开源
 * @Date 2020/9/13 4:01
 * @Version 1.0
 **/
@Configuration
@MapperScan("com.atguigu.springcloud.dao")
public class MybatisConfig {
}
