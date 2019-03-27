package com.example.producer.service;

import com.example.producer.domain.SchejobMsg;
import org.quartz.*;

import java.util.List;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/22 17:04
 * @Description: 定时任务服务接口
 */
public interface SchejobService {
    // 添加任务
    void addJob(SchejobMsg schejob);

    // 删除任务
    void deleteJob(String jobName, String jobGroup,SchejobMsg schejobMsg);

    // 暂停任务
    void pauseJob(String jobName, String jobGroup);

    // 恢复暂停的任务
    void resumeJob(String jobName, String jobGroup);

    // 查看当前正在执行的任务
    List<SchejobMsg> getRunningJob();

}
