package com.example.producer.scheduleJobs.job;

import com.example.common.domain.BrokerMessageLog;
import com.example.common.domain.Order;
import com.example.producer.domain.SchejobMsg;
import com.example.producer.mapper.BrokerMessageLogMapper;
import com.example.producer.producer.OrderSender;
import com.example.producer.service.OrderService;
import com.example.producer.service.SchejobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/22 17:01
 * @Description:定时任务类
 */
@Slf4j
@DisallowConcurrentExecution // 不允许并发执行这个类，即同一时间只能有一个实例运行这个job
public class ScheduleJob implements Job {

    @Autowired
    private OrderSender orderSender;

    @Autowired
    private OrderService orderService;

    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;

    @Autowired
    private SchejobService schejobService;


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SchejobMsg schejobMsg = (SchejobMsg) jobExecutionContext.getJobDetail().getJobDataMap().get("schejob");// 获取当前执行的定时任务中的数据
        System.out.println("----------定时任务"+schejobMsg.getJobName()+"开始执行!--------------"+new Date());
        log.info(schejobMsg.toString());
        // 获取到要发送的消息
        Order order = orderService.findOrderById(schejobMsg.getJobDataId());
        // 发送一次，重试次数减1

        if(order==null){
            System.out.println("order为空");
            return;
        }
        BrokerMessageLog brokerMessageLog = brokerMessageLogMapper.findById(order.getMessageId());
        if(brokerMessageLog!=null){

            log.info("----------------brokerMessageLog!=null执行-------------");
            Integer tryCount = brokerMessageLog.getTryCount();
            tryCount = tryCount - 1;

            if(tryCount<1){
                // 重试次数已经使用完，转入人工处理,定时任务不再执行
                schejobService.deleteJob(schejobMsg.getJobName(),schejobMsg.getJobGroup(),schejobMsg);
                System.out.println("------已经达到最大重发次数，转人工处理-------");

            }else{
                brokerMessageLog.setTryCount(tryCount);
                brokerMessageLogMapper.update(brokerMessageLog);
                // 生产端发送消息
                orderSender.send(order);
                log.info("----------------重试发送消息-------------");
            }
        }
    }
}
