package com.example.rabbitmqconsumer.consumer;

import com.example.common.domain.Order;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

import java.io.IOException;
import java.util.Map;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/19 13:33
 * @Description: 消费端，项目启动后一直监听exchange=order-exchange, queue=order-queue, routeKey=order.*的rabbitMQ
 */
@Component
@Slf4j
public class OrderReceiver {
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "order-queue",durable = "true"),
                    exchange = @Exchange(name = "order-exchange",durable = "true",type = "topic"),
                    key = "order.up" //路由
            )
    )
    @RabbitHandler
    public void onOrderMessage(@Payload Order order, @Headers Map<String,Object> headers, Channel channel) throws IOException {
        log.info("-----收到消息，开始消费------");
        log.info("-----订单id:"+order.getId());
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        // 确认消息 取值为 false 时，表示通知 RabbitMQ 当前消息被确认
        channel.basicAck(deliveryTag,false);
        log.info("------消费完成-------");
    }
}
