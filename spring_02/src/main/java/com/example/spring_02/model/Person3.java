package com.example.spring_02.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/21 11:48
 * @Description: @ImportResource注解的使用类，在启动类上加上该注解
 */
@Data //添加setting, getting, toString等方法
public class Person3 {

    private String lastName;

    private int age;

    private boolean boss;

    private Date birth;

    private Map<String, String> maps;

    private List<String> list;

    private Dog dog;

    private String hello;

    private String email;
}
