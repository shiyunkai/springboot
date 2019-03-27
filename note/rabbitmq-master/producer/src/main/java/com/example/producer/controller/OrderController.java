package com.example.producer.controller;

import com.example.common.domain.Order;
import com.example.producer.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/20 11:28
 * @Description:
 */
@RestController
@RequestMapping(value = "/order/")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "send")
    public Integer sendOrder(@RequestBody Order order){
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        order.setMessageId(order.getId().toString());
        return orderService.addOrder(order);
    }
}
