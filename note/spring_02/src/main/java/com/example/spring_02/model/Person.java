package com.example.spring_02.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/21 11:48
 * @Description: 从配置文件中读取属性值,并注册成为IOC容器bean
 */
@Component
@ConfigurationProperties(prefix = "person")
@Data //添加setting, getting, toString等方法
@Validated// 添加对属性的校验，如@Email等等  注意@Value方式注入的不支持
public class Person {

    private String lastName;

    private int age;

    private boolean boss;

    private Date birth;

    private Map<String, String> maps;

    private List<String> list;

    private Dog dog;

    private String hello;


    @Email //当注入的不是邮箱格式时会抛异常
    private String email;
}
