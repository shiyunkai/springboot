package com.example.springboot_01.conf;

import com.example.springboot_01.Baoma;
import com.example.springboot_01.Car;
import com.example.springboot_01.Toyoto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/21 10:20
 * @Description:
 */
@Configuration
public class Mycar2 {

    @Bean("toyota")
    public Car getCar1(){
        return new Toyoto();
    }

}
