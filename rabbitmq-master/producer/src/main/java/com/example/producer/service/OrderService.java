package com.example.producer.service;

import com.example.common.domain.Order;


/**
 * @Auther: shiyunkai
 * @Date: 2019/03/20 10:55
 * @Description:
 */
public interface OrderService {

    Integer addOrder(Order order);

    Order findOrderById(Integer id);
}
