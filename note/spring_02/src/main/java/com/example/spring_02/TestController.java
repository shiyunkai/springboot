package com.example.spring_02;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/21 11:30
 * @Description: 热部署测试 修改后按ctrl+f9
 */
@RestController
public class TestController {

    @RequestMapping("/hello")
    public String test(){
        return "helloo";
    }
}
