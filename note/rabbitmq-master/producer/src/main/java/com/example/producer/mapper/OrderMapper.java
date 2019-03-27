package com.example.producer.mapper;


import com.example.common.domain.Order;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/20 10:01
 * @Description:
 */
public interface OrderMapper {

    Order findOrderById(Integer id);

    Integer insert(Order order);
}
