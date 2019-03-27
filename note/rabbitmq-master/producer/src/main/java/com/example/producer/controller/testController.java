package com.example.producer.controller;

import com.example.common.domain.Order;
import com.example.producer.domain.SchejobMsg;
import com.example.producer.producer.OrderSender;
import com.example.producer.service.SchejobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/20 09:37
 * @Description:
 */
@RestController
@Slf4j
public class testController {

    @Autowired
    private SchejobService schejobService;

    @Autowired
    private OrderSender orderSender;

    @GetMapping("/test")
    public String test(){
        Order order = new Order();
        order.setId(1);
        order.setMessageId(System.currentTimeMillis()+"10");
        orderSender.send(order);
        return "success";
    }

    @PostMapping("/addScheduleJob")
    public String testSchedule(@RequestBody SchejobMsg schejobMsg){
        schejobMsg.setCreateTime(new Date());
        schejobService.addJob(schejobMsg);
        return "success";
    }
}
