package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description TODO
 * @Author 张开源
 * @Date 2020/8/27 1:03
 * @Version 1.0
 **/
@Configuration
public class GateWayConfig {
/*
    配置了一个id为route.name的路由规则,当访问http://localhost:9527/guonei时会自动转发到地址http://news.baidu.com/guonei
*/
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route_atguigu",r ->r.path("/guonei")
        .uri("http://news.baidu.com/guonei")).build();
        return routes.build();
    }
}
