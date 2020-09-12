package com.atguigu.springcloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Description TODO
 * @Author 张开源
 * @Date 2020/9/13 6:18
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    /*主键id*/
    private Long id;
    /*用户id*/
    private Long userId;
    /*总额度*/
    private BigDecimal total;
    /*已用额度*/
    private BigDecimal used;
    /*剩余额度*/
    private BigDecimal residue;
}
