package com.example.producer.conf.init;

import com.example.producer.domain.SchejobMsg;
import com.example.producer.service.SchejobService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/23 10:42
 * @Description: 配置项目一启动就执行的定时器相关的操作
 */
@Component
public class ScheduleComponent implements CommandLineRunner {

    @Autowired
    SchejobService schejobService;

    // 项目启动时会执行这个函数
    @Override
    public void run(String... args) throws Exception {
        System.out.println("--------run执行!---------");
        List<SchejobMsg> runningJobs = schejobService.getRunningJob();
        System.out.println("-------当前正在执行的任务数为:"+runningJobs.size());
        for (SchejobMsg sm:runningJobs){
            System.out.println(sm.getJobName()+" "+sm.getJobGroup());
        }
    }
}
