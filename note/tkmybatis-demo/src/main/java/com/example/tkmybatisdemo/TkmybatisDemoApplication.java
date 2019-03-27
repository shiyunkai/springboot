package com.example.tkmybatisdemo;

import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Locale;
import java.util.Properties;

@SpringBootApplication
@MapperScan(basePackages = "com.example.tkmybatisdemo.dao")
public class TkmybatisDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TkmybatisDemoApplication.class, args);
    }

}
