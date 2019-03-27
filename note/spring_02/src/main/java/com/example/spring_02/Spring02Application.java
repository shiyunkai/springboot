package com.example.spring_02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource(locations = {"classpath:mybeans.xml"})
@SpringBootApplication
public class Spring02Application {

    public static void main(String[] args) {
        SpringApplication.run(Spring02Application.class, args);
    }

}
