package com.example.producer.producer;

import com.example.common.domain.BrokerMessageLog;
import com.example.common.domain.Order;
import com.example.producer.constants.StatusEnum;
import com.example.producer.domain.SchejobMsg;
import com.example.producer.mapper.BrokerMessageLogMapper;
import com.example.producer.service.SchejobService;
import com.example.producer.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;


import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * @Auther: shiyunkai
 * @Date: 2019/03/19 14:05
 * @Description: 订单发送端
 */
@Slf4j
@Component
public class OrderSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;

    @Autowired
    private SchejobService schejobService;


    // 要使回调函数生效，配置文件中要加 publisher-confirms: true 和 publisher-returns: true
    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            if(ack==true){
                //生产者端接受MQ Broker节点返回的Confirm确认消息结果，然后进行更新消息记录表里的消息状态。
                log.info("-------消息已被确认!-------:"+correlationData);
                BrokerMessageLog brokerMessageLog = brokerMessageLogMapper.findById(correlationData.getId());
                brokerMessageLog.setStatus(StatusEnum.ORDER_SEND_SUCCESS.getMessage());
                brokerMessageLogMapper.update(brokerMessageLog);

            }else{
                log.info("--------消息{}确认出现异常!-----------",correlationData);
                // 此时说明消息发送失败，要进行消息的重新发送，使用定时任务处理
                BrokerMessageLog brokerMessageLog = brokerMessageLogMapper.findById(correlationData.getId());
                brokerMessageLog.setStatus(StatusEnum.ORDER_SEND_FAILURE.getMessage());//设置消息状态为发送失败
                log.info("----brokerMessageLog是-----"+brokerMessageLog.toString());

                SchejobMsg schejobMsg = new SchejobMsg();
                schejobMsg.setCreateTime(new Date());
                schejobMsg.setJobDataId(brokerMessageLog.getSubjectId());//设置任务中的数据的id
                schejobMsg.setJobGroup("message_retry");
                schejobMsg.setJobName(correlationData.getId().toString());
                schejobMsg.setCronExperssion(DateUtils.DateToCron(new Date(System.currentTimeMillis()+15000)));//将定时任务的执行时间设置为当前时间之后的15秒钟
                // 添加重新发送消息的定时任务
                schejobService.addJob(schejobMsg);

            }
        }
    };

    /* * @Auther: shiyunkai
     * @Description: 发送消息
     * @Param:
     * @Date:  14:41 2019/3/19
     * @return: void
     **/
    public void send(Order order){
        // 设置投递回调函数
        rabbitTemplate.setConfirmCallback(confirmCallback);
        CorrelationData correlationData = new CorrelationData();
        // 设置消息唯一id
        correlationData.setId(order.getMessageId());
        rabbitTemplate.convertAndSend("order-exchange11","order.up",order,correlationData);
        log.info("--------正在进行消息投递----------");
    }
}
