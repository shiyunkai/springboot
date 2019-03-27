package com.example.producer.service.impl;

import com.example.common.domain.BrokerMessageLog;
import com.example.common.domain.Order;
import com.example.producer.constants.StatusEnum;
import com.example.producer.mapper.BrokerMessageLogMapper;
import com.example.producer.mapper.OrderMapper;
import com.example.producer.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.producer.producer.OrderSender;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/20 10:56
 * @Description:
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;

    @Autowired
    private OrderSender orderSender;

    @Override
    public Integer addOrder(Order order) {
        // 插入订单
        Integer id = orderMapper.insert(order);
        // 插入消息记录表数据
        BrokerMessageLog brokerMessageLog = new BrokerMessageLog();
        brokerMessageLog.setId(order.getId().toString());
        brokerMessageLog.setSubjectId(order.getId());
        brokerMessageLog.setTryCount(3);//设置发送失败后的最大重发次数为3
        // 设置消息状态为0表示发送中
        brokerMessageLog.setStatus(StatusEnum.ORDER_SENDING.getMessage());
        // 设置重发时间为订单创建时间后的1分钟
        brokerMessageLog.setNextRetry(new Date(order.getCreateTime().getTime()+60*1000));
        brokerMessageLog.setCreateTime(new Date());
        brokerMessageLog.setUpdateTime(new Date());
        brokerMessageLogMapper.insert(brokerMessageLog);

        // 发送消息
        orderSender.send(order);

        return id;
    }

    @Override
    public Order findOrderById(Integer id) {
        return orderMapper.findOrderById(id);
    }
}
