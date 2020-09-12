package com.atguigu.springcloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @Author 张开源
 * @Date 2020/9/13 5:39
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Storage {
    /*主键id*/
    private Long id;
    /*产品id*/
    private Long productId;
    /*总库存*/
    private Integer total;
    /*已用库存*/
    private Integer used;
    /*剩余库存*/
    private Integer residue;
}
