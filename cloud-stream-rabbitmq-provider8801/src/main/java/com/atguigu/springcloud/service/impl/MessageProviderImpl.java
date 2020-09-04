package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;


/**
 * @Description TODO
 * @Author 张开源
 * @Date 2020/9/4 11:23
 * @Version 1.0
 **/
@EnableBinding(Source.class) //定义消息推送管道
@Slf4j
public class MessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output;//消息发送管道

    @Override
    public String send() {
        UUID randomUUID = UUID.randomUUID();
        log.info("uuid:"+randomUUID);
        output.send(MessageBuilder.withPayload(randomUUID).build());
        log.info("********uuid:"+randomUUID);
        return "发送完成";
    }
}
