package com.example.springboot_01.conf;

import com.example.springboot_01.Baoma;
import com.example.springboot_01.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/21 10:20
 * @Description:
 */
@Configuration
public class Mycar1 {

    @Bean("baoma")
    public Car getCar1(){
        return new Baoma();
    }

}
