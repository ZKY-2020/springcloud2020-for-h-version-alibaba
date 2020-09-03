package com.atguigu.springcloud.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommonResult <T>{
    /*返回错误码*/
    private Integer code;
    /*返回错误信息*/
    private String message;

    private T    data;

    public CommonResult(Integer code, String message,T data) {
        this.code=code;
        this.message=message;
        this.data=data;
    }
}
