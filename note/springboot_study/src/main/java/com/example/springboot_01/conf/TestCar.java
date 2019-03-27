package com.example.springboot_01.conf;

import com.example.springboot_01.Car;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.annotation.Annotation;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/21 10:23
 * @Description:
 */
public class TestCar {

    public static void main(String[] args) {
        // 获取容器   参数: ParentConfiguration.class相当于以前的配置文件
        AnnotationConfigApplicationContext ap = new AnnotationConfigApplicationContext(ParentConfiguration.class);
        Car toyota = (Car) ap.getBean("toyota");
        Car bm = (Car) ap.getBean("baoma");
        toyota.print();
        bm.print();
    }
}
