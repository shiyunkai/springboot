package com.example.springboot_03;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/21 19:31
 * @Description:
 */
@Data
public class Person {

    private String name;

    //自定义转换给前端的json格式，此时添加的阿里的fastjson消息转换器会失效
    //@JSONField(format = "yyyy-MM-dd")
    private Date birthday;
}
