package com.example.producer;

import com.example.common.domain.BrokerMessageLog;
import com.example.common.domain.Order;
import com.example.producer.domain.SchejobMsg;
import com.example.producer.mapper.BrokerMessageLogMapper;
import com.example.producer.producer.OrderSender;
import com.example.producer.service.SchejobService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProducerApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private OrderSender orderSender;

    @Test
    public void testSend(){
        Order order = new Order();
        order.setId(1);
        order.setMessageId(System.currentTimeMillis()+"10");
        orderSender.send(order);
        log.info("-------发送完成-------");

    }

    @Autowired
    SchejobService schejobService;

    /* * @Auther: shiyunkai
     * @Description: 测试向数据库中插入schejob
     * @Param:
     * @Date:  9:01 2019/3/23
     * @return: void
     **/
    @Test
    public void HelloScheduler(){
        SchejobMsg schejob = new SchejobMsg();
        schejob.setCreateTime(new Date());
        schejob.setJobName("吃饭");
        schejob.setJobGroup("123");
        schejob.setCronExperssion("0/10 * * * * ? *");
        schejob.setJobStatus("开启");

        schejobService.addJob(schejob);
    }

    @Autowired
    BrokerMessageLogMapper brokerMessageLogMapper;
    @Test
    public void testBrokerMessageLogMapper(){

        log.info("---------测试!");
        BrokerMessageLog brokerMessageLog = brokerMessageLogMapper.findById("8");
        log.info(brokerMessageLog.toString());
    }


}
