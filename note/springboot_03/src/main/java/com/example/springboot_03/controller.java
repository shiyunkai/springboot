package com.example.springboot_03;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/21 19:32
 * @Description:
 */
@RestController
public class controller {

    @GetMapping("/hello")
    public Person hello(){
        Person person = new Person();
        person.setBirthday(new Date());
        person.setName("xiaoming");
        return person;
    }

    @GetMapping("/test")
    public String hello2(){
        return "test!";
    }
}
