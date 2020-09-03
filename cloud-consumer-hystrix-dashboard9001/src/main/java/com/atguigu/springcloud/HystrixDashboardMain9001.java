package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @Description TODO
 * @Author 张开源
 * @Date 2020/8/25 19:52
 * @Version 1.0
 **/
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardMain9001 {
   public static void main(String[] args) {
      SpringApplication.run(HystrixDashboardMain9001.class,args);
   }
}
