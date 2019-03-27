package com.example.springboot_01.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/21 10:22
 * @Description:
 */
@Configuration
@Import({Mycar1.class,Mycar2.class}) //将Mycar1和Mycar2合并在一个容器中
public class ParentConfiguration {
}
