package com.example.springboot_01;

import org.springframework.context.annotation.Configuration;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/21 10:18
 * @Description:
 */
public class Toyoto implements Car{
    @Override
    public void print() {
        System.out.print("我是toyoto\n");
    }
}
