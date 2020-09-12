package com.atguigu.springcloud.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @Author 张开源
 * @Date 2020/9/13 5:37
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
public class CommonResult<T>{
    /*返回错误码*/
    private Integer code;
    /*返回错误信息*/
    private String message;

    private T    data;

    public CommonResult(Integer code, String message, T data) {
        this.code=code;
        this.message=message;
        this.data=data;
    }
}
